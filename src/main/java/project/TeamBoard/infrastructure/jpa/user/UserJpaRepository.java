package project.TeamBoard.infrastructure.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.infrastructure.entity.UserEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Integer> {

}
