package project.TeamBoard.infrastructure.jpa;

import project.TeamBoard.domain.board.Board;
import project.TeamBoard.domain.user.User;

import java.util.Optional;

public interface BoardRepository {

//    Optional<Board> findById(String email);

    Board save(Board board);

    Board findBoardDetail(Long boardId);

    Board updateBoard(Board board);

    Long deleteBoard(Long boardId);


//    boolean existsByEmail(String email);
}
