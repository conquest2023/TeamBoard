package project.TeamBoard.interfaces.dto;

import lombok.Builder;

@Builder
public record JwtToken(
        String accessToken,

        String grantType,
        String refreshToken
) {
}
