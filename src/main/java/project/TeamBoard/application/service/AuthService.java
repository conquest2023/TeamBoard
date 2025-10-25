package project.TeamBoard.application.service;

import project.TeamBoard.application.command.LoginUserCommand;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.application.command.CreateUserCommand;
import project.TeamBoard.interfaces.dto.JwtToken;

public interface AuthService {



    User signUp(CreateUserCommand create);


    JwtToken login(LoginUserCommand login);


}
