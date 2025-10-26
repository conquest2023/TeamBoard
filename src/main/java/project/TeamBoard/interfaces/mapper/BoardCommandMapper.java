package project.TeamBoard.interfaces.mapper;

import org.springframework.stereotype.Component;
import project.TeamBoard.application.command.board.BoardDetailCommand;
import project.TeamBoard.application.command.board.CreateBoardCommand;
import project.TeamBoard.application.command.board.UpdateBoardCommand;
import project.TeamBoard.interfaces.dto.BoardDetail;
import project.TeamBoard.interfaces.dto.CreateBoard;
import project.TeamBoard.interfaces.dto.UpdateBoard;

@Component
public class BoardCommandMapper {

    public CreateBoardCommand toCommand(CreateBoard dto) {
        return new CreateBoardCommand(
                dto.title(),
//                dto.description(),
                dto.email(),
                dto.name(),
                dto.archived(),
                dto.createdAt(),
                dto.updatedAt()
        );
    }
    public UpdateBoardCommand toCommand(UpdateBoard dto) {
        return new UpdateBoardCommand(
                dto.id(),
                dto.name(),
                dto.archived(),
                dto.version(),
                dto.updatedAt()
        );
    }

    public BoardDetailCommand toCommand(BoardDetail dto) {
        return new BoardDetailCommand(
                dto.id(),
                dto.projectId(),
                dto.name(),
                dto.email(),
                dto.archived(),
                dto.version(),
                dto.createdAt(),
                dto.updatedAt()
//                dto.columns()
        );
    }
}