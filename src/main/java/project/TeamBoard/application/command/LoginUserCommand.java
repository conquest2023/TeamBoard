package project.TeamBoard.application.command;

public record LoginUserCommand(

        String email,
//        String username,

        String clientIp,
        String rawPassword
) {
}
