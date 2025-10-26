package project.TeamBoard.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.TeamBoard.domain.board.Board;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.infrastructure.jpa.user.UserJpaRepository;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;

import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements BoardRepository {

    private final BoardRepository repository;


    @Override
    public Board save(Board board) {
        return null;
    }

    @Override
    public  Board findBoardDetail(Long boardId) {
        return null;
    }

    @Override
    public Board updateBoard(Board board) {
        return null;
    }

    @Override
    public Long deleteBoard(Long boardId) {
        return null;
    }
}
