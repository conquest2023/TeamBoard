package project.TeamBoard.infrastructure.jpa.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.TeamBoard.domain.user.User;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements  UserRepository{

    private final UserJpaRepository repository;

    @Override
    public Optional<User> findById(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {

       return repository.save(
                User.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .lastLogin(LocalDateTime.now())
                        .build());
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
