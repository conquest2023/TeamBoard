package project.TeamBoard.interfaces.mapper;


import org.springframework.stereotype.Component;
import project.TeamBoard.application.command.*;
import project.TeamBoard.application.command.board.BoardDetailCommand;
import project.TeamBoard.application.command.board.CreateBoardCommand;
import project.TeamBoard.domain.board.Board;

@Component
public class BoardDomainToCommandMapper {

    public BoardDetailCommand toDetailCommand(Board board) {
        return new BoardDetailCommand(
                board.getId(),
                board.getProjectId(),
                board.getName(),
                board.getEmail(),
                board.isArchived(),
                board.getVersion(),
                board.getCreatedAt(),
                board.getUpdatedAt()
//                board.getColumns()  // 컬럼 도메인이 다르면 변환 로직 추가
        );
    }

//    public UpdateBoardCommand toUpdateCommand(Board board) {
//        return new UpdateBoardCommand(
//                board.getId(),
//                board.getName(),
//                board.isArchived(),
//                board.getVersion(),
//                board.getUpdatedAt()
//        );
//    }

    public CreateBoardCommand toCreateCommand(Board board) {
        return new CreateBoardCommand(
                board.getTitle(),
                board.getName(),          // = title
                board.getEmail(),
//                board.getDescription(),
                board.isArchived(),
                board.getCreatedAt(),
                board.getUpdatedAt()
//                board.getColumns()
        );
    }
}

