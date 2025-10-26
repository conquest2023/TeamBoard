package project.TeamBoard.infrastructure.jpa.user.test;

import org.springframework.stereotype.Repository;
import project.TeamBoard.domain.board.Board;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.infrastructure.jpa.BoardRepository;
import project.TeamBoard.infrastructure.jpa.user.UserRepository;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class TestBoardPersistenceAdapter implements BoardRepository {

    private final Map<Long, Board> store;


    public TestBoardPersistenceAdapter(Map<Long, Board> store) {
        this.store = store;
    }

    @Override
    public Board save(Board board) {
        Board newBoard= new Board();
        newBoard.setId(1L);
        newBoard.setName(board.getName());
        newBoard.setEmail(board.getEmail());
        newBoard.setTitle(board.getTitle());
        store.put(newBoard.getId(),newBoard);
        return newBoard;
    }

    @Override
    public Board findBoardDetail(Long boardId) {

        return store.get(boardId);
    }

    @Override
    public Board updateBoard(Board board) {
     return  Board.builder()
                .id(board.getId())
                .name(board.getName())
                .updatedAt(Instant.now())
                .build();
    }

    @Override
    public Long deleteBoard(Long boardId) {

        boolean check = store.containsKey(boardId);
        if (check){
            store.remove(boardId);
        }else {
            throw new RuntimeException("게시물이 존재하지 않습니다.");
        }
        return boardId;
    }
}
