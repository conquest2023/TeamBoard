package project.TeamBoard.domain.board;

import lombok.*;

import java.time.Instant;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private Long id;                 // Board PK
    private Long projectId;          // 소속 프로젝트
    private String name;             // 보드 이름 (프로젝트 내 유니크)

    private String title;

    private String email;

    private Long version;

    private boolean archived;        // 보드 아카이브 여부

    private Instant createdAt;

    private Instant updatedAt;

}
