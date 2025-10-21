package project.TeamBoard.infrastructure.jpa.user;

import org.springframework.stereotype.Repository;
import project.TeamBoard.domain.user.User;

import java.util.Optional;

@Repository
public class UserPersistenceAdapter implements  UserRepository{
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
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
