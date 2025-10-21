package project.TeamBoard.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
public class User {


    public User(String email, String username, String password, LocalDateTime createdAt) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }


    private int id;

    private String email;

//    private String userId;

    private String username;

    private String password;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLogin;

}
