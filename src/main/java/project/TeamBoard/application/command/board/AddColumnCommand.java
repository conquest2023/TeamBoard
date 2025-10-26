package project.TeamBoard.application.command.board;

public record AddColumnCommand(
        Long boardId,
        String name,
        Integer wipLimit,         // null = 무제한
        Integer desiredOrderIdx,  // null이면 자동(간격 인덱싱)
        Long actorId
) {}
