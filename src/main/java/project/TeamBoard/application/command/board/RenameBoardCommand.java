package project.TeamBoard.application.command.board;

public record RenameBoardCommand(
        Long boardId,
        String newName,
        Long actorId,
        Long expectedVersion
) {}