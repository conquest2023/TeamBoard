package project.TeamBoard.interfaces.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SignUpDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class Request{
        private String email;

        private String username;

        private String password;

    }

    @Data
    public static  class Response{
        private String email;

        private String username;

        private String password;

    }


}
