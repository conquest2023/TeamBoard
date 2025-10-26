package project.TeamBoard.application.command.board;

public record SetBoardArchivedCommand(
        Long boardId,
        boolean archived,
        Long actorId,
        Long expectedVersion
) {}
