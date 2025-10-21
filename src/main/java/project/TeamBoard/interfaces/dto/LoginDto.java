package project.TeamBoard.interfaces.dto;

import lombok.Data;

public class LoginDto {

    @Data
    public static class Request{
       private String email;
       private String password;

    }
    @Data
    public static class Response{

        private String email;
        private String password;
    }
}
