package project.TeamBoard.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "boards",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_boards_project_name", columnNames = {"project_id", "name"})
        }
)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 프로젝트 연관: 간단히 스칼라 FK로 유지 (ProjectEntity 연결은 추후 필요 시)
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    // 낙관적 락
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // 컬럼 목록 (필요 시만 로딩)
//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @OrderBy("orderIdx ASC")
//    @Builder.Default
//    private List<BoardColumnEntity> columns = new ArrayList<>();
}
