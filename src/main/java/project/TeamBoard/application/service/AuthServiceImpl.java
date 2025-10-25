package project.TeamBoard.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.TeamBoard.application.command.LoginUserCommand;
import project.TeamBoard.config.jwt.JwtTokenProvider;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.application.command.CreateUserCommand;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;
import project.TeamBoard.interfaces.dto.JwtToken;
import project.TeamBoard.interfaces.session.AttemptInfo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder;
    private static final Duration WINDOW = Duration.ofMinutes(10);

    private static final Duration LOCK_DURATION = Duration.ofMinutes(10);

    private final Map<String, AttemptInfo> attemptInfoMap=new ConcurrentHashMap<>();


    @Override
    public User signUp(CreateUserCommand create) {
        if(userRepository.existsByEmail(create.email())){
            throw new RuntimeException("이미 사용 중인 이메일입니다: " + create.email());
        }
        validatePassword(create.rawPassword());
        User user=new User(create.email(),
                create.username(),
                passwordEncoder.encode(create.rawPassword()),
                LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @Override
    public JwtToken login(LoginUserCommand login) {
        Optional<User> user = userRepository.findByEmail(login.email());
        registerFailure(login.email()+login.clientIp());
        if (isLocked(login.email()+login.clientIp())){
            throw new RuntimeException("10분간 제한되셨습니다.");
        }
        if (user.isPresent() && passwordEncoder.matches(login.rawPassword(),user.get().getPassword())){
            userRepository.save(user.get());
//            jwtTokenProvider.generateToken(login.email());
            return jwtTokenProvider.generateToken(user.get().getEmail());
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
        if (info.lockUntil==null)
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
