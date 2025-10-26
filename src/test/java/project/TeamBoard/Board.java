package project.TeamBoard;

import jakarta.persistence.Table;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.TeamBoard.application.command.board.CreateBoardCommand;
import project.TeamBoard.application.service.BoardService;
import project.TeamBoard.application.service.BoardServiceImpl;
import project.TeamBoard.infrastructure.jpa.user.test.TestBoardPersistenceAdapter;
import project.TeamBoard.interfaces.mapper.BoardDomainToCommandMapper;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Board {


    @Test
    void 게시물쓰기(){

        BoardService service=new BoardServiceImpl(new TestBoardPersistenceAdapter(new HashMap<>()),new BoardDomainToCommandMapper());

        project.TeamBoard.domain.board.Board board = service.createBoard(
                new CreateBoardCommand(
                        "hello",
                        "hi",
                        "sad",
                        true,
                        Instant.now(),
                        Instant.now())
        );
        System.out.println(board.getId());
        System.out.println(board.getName());
        Assertions.assertThat(board.getTitle()).isEqualTo("hello");
    }

    @Test
    void 게시물가져오기(){
        Map<Long, project.TeamBoard.domain.board.Board> map=new HashMap<>();
        BoardService service=new BoardServiceImpl(new TestBoardPersistenceAdapter(map),new BoardDomainToCommandMapper());
        TestBoardPersistenceAdapter repository = new TestBoardPersistenceAdapter(map);
        project.TeamBoard.domain.board.Board board = service.createBoard(
                new CreateBoardCommand(
                        "hello",
                        "hi",
                        "sad",
                        true,
                        Instant.now(),
                        Instant.now())
        );
        project.TeamBoard.domain.board.Board boardDetail = repository.findBoardDetail(board.getId());


        Assertions.assertThat(boardDetail.getTitle()).isEqualTo("hello");

    }

    @Test
    void 업데이트(){
        Map<Long, project.TeamBoard.domain.board.Board> map=new HashMap<>();
        BoardService service=new BoardServiceImpl(new TestBoardPersistenceAdapter(map),new BoardDomainToCommandMapper());
        TestBoardPersistenceAdapter repository = new TestBoardPersistenceAdapter(map);
        project.TeamBoard.domain.board.Board board = service.createBoard(
                new CreateBoardCommand(
                        "hello",
                        "hi",
                        "sad",
                        true,
                        Instant.now(),
                        Instant.now())
        );

//        repository.updateBoard(board)
    }
}
