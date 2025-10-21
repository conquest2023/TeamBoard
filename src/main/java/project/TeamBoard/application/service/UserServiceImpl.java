package project.TeamBoard.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.TeamBoard.application.command.LoginUserCommand;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.application.command.CreateUserCommand;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;


    @Override
    public User signUp(CreateUserCommand create) {
        if(userRepository.existsByEmail(create.email())){
            throw new RuntimeException("이미 사용 중인 이메일입니다: " + create.email());
        }
        User user=new User(create.email(),
                create.username(),
                create.rawPassword(),
                LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(LoginUserCommand login) {
        return null;
    }


}
