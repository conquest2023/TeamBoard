package project.TeamBoard.interfaces.mapper;

import org.springframework.stereotype.Component;
import project.TeamBoard.infrastructure.entity.BoardEntity;
import project.TeamBoard.interfaces.dto.BoardDetail;
import project.TeamBoard.interfaces.dto.ColumnSummary;
import project.TeamBoard.interfaces.dto.CreateBoard;
import project.TeamBoard.interfaces.dto.UpdateBoard;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardMapper {

//    public BoardDetail toBoardDetail(BoardEntity entity) {
//        List<ColumnSummary> columns = entity.getColumns().stream()
//                .map(this::toColumnSummary)
//                .collect(Collectors.toList());
//
//        return new BoardDetail(
//                entity.getId(),
//                entity.getProjectId(),
//                entity.getName(),
//                entity.isArchived(),
//                entity.getVersion(),
//                entity.getCreatedAt(),
//                entity.getUpdatedAt(),
//                columns
//        );
//    }

//    public ColumnSummary toColumnSummary(BoardColumnEntity col) {
//        return new ColumnSummary(
//                col.getId(),
//                col.getBoard().getId(),
//                col.getName(),
//                col.getWipLimit(),
//                col.getOrderIdx(),
//                col.getCreatedAt(),
//                col.getUpdatedAt()
//        );
//    }

    // ── CreateBoard → Entity 변환 ───────────────────────────────
    public BoardEntity fromCreateDto(Long projectId, CreateBoard dto) {
        return BoardEntity.builder()
                .projectId(projectId)
                .name(dto.title())     // dto.title → Entity.name
                .archived(dto.archived())
                .version(dto.version() != null ? dto.version() : 0L)
                .build();
    }

    // ── UpdateBoard → Entity 병합용 ───────────────────────────────
//    public void merge(BoardEntity entity, UpdateBoard dto) {
//        if (dto.name() != null) {
//            entity.setName(dto.name());
//        }
//        entity.setArchived(dto.archived());
//        // version은 @Version이 자동 증가하므로 setter 불필요
//    }

    // ── Entity → CreateBoard (응답용) ───────────────────────────────
//    public CreateBoard toCreateBoard(BoardEntity entity) {
//        List<ColumnSummary> columns = entity.getColumns().stream()
//                .map(this::toColumnSummary)
//                .collect(Collectors.toList());
//
//        return new CreateBoard(
//                entity.getName(),
//                null,                       // description: 아직 없음
//                entity.isArchived(),
//                entity.getVersion(),
//                entity.getCreatedAt(),
//                entity.getUpdatedAt(),
//                columns
//        );
//    }
}
