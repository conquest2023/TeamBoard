package project.TeamBoard.interfaces.mapper;

import project.TeamBoard.application.command.CreateUserCommand;
import project.TeamBoard.interfaces.dto.SignUpDto;

public class UserMapper {

    public static CreateUserCommand toCreateCommand(SignUpDto.Request req) {
        return new CreateUserCommand(req.getEmail(), req.getUsername(), req.getPassword());
    }

    // Domain → Response DTO (민감정보 배제)
//    public static LoginDto.Request toResponse(User user) {
//        return new LoginDto.Request(
//                user.getId(),
//                user.getEmail(),
//                user.getUsername(),
//                user.getCreatedAt(),
//                user.getLastLogin()
//        );
//    }
}
