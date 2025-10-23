package project.TeamBoard.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.TeamBoard.application.command.CreateUserCommand;
import project.TeamBoard.application.command.LoginUserCommand;
import project.TeamBoard.application.service.UserService;
import project.TeamBoard.application.service.UserServiceImpl;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;
import project.TeamBoard.infrastructure.jpa.user.test.TestPersistenceAdapter;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class TestUser {




    @Test
    void 회원가입(){

        UserService service=new UserServiceImpl(new TestPersistenceAdapter());

        User user = service.signUp(new CreateUserCommand("hi", "123", "123"));

        Assertions.assertThat(user.getEmail()).isEqualTo("hi");
    }


    @Test
    void 중복회원체크(){
        UserRepository repository=new TestPersistenceAdapter();

        repository.save(new User("hi@example.com", "주형", "hash", LocalDateTime.now()));
        boolean exists = repository.existsByEmail("hi@example.com");
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    void 아이디가져오기(){
        UserRepository repository=new TestPersistenceAdapter();
        repository.save(new User("hi@example.com", "주형", "hash", LocalDateTime.now()));
        Optional<User> user = repository.findByEmail("hi@example.com");
        Assertions.assertThat(user.get().getEmail()).isEqualTo("hi@example.com");
    }

    @Test
    void 로그인(){
        UserRepository repository=new TestPersistenceAdapter();
        UserService userService=new UserServiceImpl(repository);
        repository.save(new User("hi@example.com", "주형", "hash", LocalDateTime.now()));
        User user = userService.login(new LoginUserCommand("hi@example.com","12","hash"));
        User user2 = userService.login(new LoginUserCommand("hi@example.com","12","hash"));
        User user3= userService.login(new LoginUserCommand("hi@example.com","12","hash"));
        User user4 = userService.login(new LoginUserCommand("hi@example.com","12","hash"));
        User user5 = userService.login(new LoginUserCommand("hi@example.com","12","hash"));
        User user6 = userService.login(new LoginUserCommand("hi@example.com","12","hash"));




        Assertions.assertThat(user.getEmail()).isEqualTo("hi@example.com");
        Assertions.assertThat(user.getPassword()).isEqualTo("hash");
    }

}
