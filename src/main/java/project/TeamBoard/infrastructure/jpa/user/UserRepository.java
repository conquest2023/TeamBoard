package project.TeamBoard.infrastructure.jpa.user;

import project.TeamBoard.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String email);

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean existsByEmail(String email);
}
