package project.TeamBoard.infrastructure.jpa.user.test;

import org.springframework.stereotype.Repository;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class TestPersistenceAdapter implements UserRepository {

    private final Map<String, User> store;

    public TestPersistenceAdapter() {
        this.store = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(store.get(email));
    }

    @Override
    public User save(User user) {
        store.put(user.getEmail(),user);
        return user;
    }
    @Override
    public boolean existsByEmail(String email) {

        return  store.containsKey(email);
    }

    public void clear() {
        store.clear();
    }

    public Map<String, User> snapshot() {
        return Map.copyOf(store);
    }

}
