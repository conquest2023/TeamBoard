package project.TeamBoard.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.TeamBoard.application.command.CreateUserCommand;
import project.TeamBoard.application.command.LoginUserCommand;
import project.TeamBoard.application.service.AuthService;
import project.TeamBoard.application.service.AuthServiceImpl;
import project.TeamBoard.config.jwt.JwtTokenProvider;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;
import project.TeamBoard.infrastructure.jpa.user.test.TestUserPersistenceAdapter;
import project.TeamBoard.interfaces.dto.JwtToken;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest

public class TestUser {




    @Test
    void 회원가입(){

        AuthService service=new AuthServiceImpl(new TestUserPersistenceAdapter(),new JwtTokenProvider("d1esd"),new BCryptPasswordEncoder());

        User user = service.signUp(new CreateUserCommand("hi", "123", "123"));

        Assertions.assertThat(user.getEmail()).isEqualTo("hi");
    }


    @Test
    void 중복회원체크(){
        UserRepository repository=new TestUserPersistenceAdapter();

        repository.save(new User("hi@example.com", "주형", "hash", LocalDateTime.now()));
        boolean exists = repository.existsByEmail("hi@example.com");
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    void 아이디가져오기(){
        UserRepository repository=new TestUserPersistenceAdapter();
        repository.save(new User("hi@example.com", "주형", "hash", LocalDateTime.now()));
        Optional<User> user = repository.findByEmail("hi@example.com");
        Assertions.assertThat(user.get().getEmail()).isEqualTo("hi@example.com");
    }

    @Test
    void 로그인(){

        UserRepository repository=new TestUserPersistenceAdapter();
        AuthService userService=new AuthServiceImpl(repository,new JwtTokenProvider("6f6bcc816277ce71c79931fdfb820c1926ec0d9bcfdbdff61ff257e5de6fa1c4"),new BCryptPasswordEncoder());
        User user = userService.signUp(new CreateUserCommand("hi@example.com", "123", "a12345678"));


        JwtToken token = userService.login(new LoginUserCommand("hi@example.com", null, "a12345678"));
        System.out.println(token.refreshToken());
    }

}
