package project.TeamBoard.infrastructure.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.TeamBoard.domain.user.User;

public interface UserJpaRepository extends JpaRepository<User,Integer> {
}
