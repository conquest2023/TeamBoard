package project.TeamBoard.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.TeamBoard.application.command.LoginUserCommand;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.application.command.CreateUserCommand;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;
import project.TeamBoard.interfaces.session.AttemptInfo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.awt.SystemColor.WINDOW;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private static final Duration WINDOW = Duration.ofMinutes(10);
    private static final int THRESHOLD = 5;
    private static final Duration LOCK_DURATION = Duration.ofMinutes(10);
    private static final Duration MAX_LOCK = Duration.ofHours(24); // (옵션)

    private final Map<String, AttemptInfo> attemptInfoMap=new ConcurrentHashMap<>();


    @Override
    public User signUp(CreateUserCommand create) {
        if(userRepository.existsByEmail(create.email())){
            throw new RuntimeException("이미 사용 중인 이메일입니다: " + create.email());
        }
        validatePassword(create.rawPassword());
        User user=new User(create.email(),
                create.username(),
                create.rawPassword(),
                LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(LoginUserCommand login) {
        Optional<User> user = userRepository.findByEmail(login.email());
        registerFailure(login.email()+login.clientIp());
        if (isLocked(login.email()+login.clientIp())){
            throw new RuntimeException("10분간 제한되셨습니다.");
        }
        if (user.isPresent() && user.get().getPassword().equals(login.rawPassword())){
            userRepository.save(user.get());
            return user.get();
        } else{
            throw new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    private void registerFailure(String key) {
        Instant now = Instant.now();
        attemptInfoMap.compute(key, (k, info) -> {
            if (info == null) {
                return new AttemptInfo(1, now, now, null);
            }
            if (info.getLockUntil()!= null && now.isBefore(info.getLockUntil())) {
                // already locked — keep as is
                return info;
            }
            // 윈도우 검사
            if (Duration.between(info.getFirstFailureAt(), now).compareTo(WINDOW) > 0) {
                // 새 윈도우: reset
                info.count= 1;
                info.firstFailureAt= now;
                info.lastFailureAt = now;
                info.lockUntil = null;
            } else {
                info.count += 1;
                info.lastFailureAt = now;
                if (info.count >= 5) {
                    info.lockUntil = now.plus(LOCK_DURATION);
                }
            }
            return info;
        });
    }


    private void validatePassword(String rawPassword) {
        if (rawPassword.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
        if (!rawPassword.matches(".*[A-Za-z].*") || !rawPassword.matches(".*\\d.*")) {
            throw new IllegalArgumentException("비밀번호는 영문자와 숫자를 모두 포함해야 합니다.");
        }

    }

    private boolean isLocked(String key) {
        AttemptInfo info = attemptInfoMap.get(key);
        if (info == null)
            return false;
        Instant now = Instant.now();
        if (info.lockUntil != null && now.isBefore(info.lockUntil))
            return true;
        // expired lock can be cleared optionally
        return false;
    }

    private void clearAttempts(String key) {
        attemptInfoMap.remove(key);
    }

}
