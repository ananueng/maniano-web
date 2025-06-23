package dev.ananueng.maniano.utilities;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Integer id;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime deletedAt;

    private String createdBy;
    private String lastModifiedBy;
    private String deletedBy;

    private boolean deleted = false;

    // persist is the state before the entity is managed by the entity manager
    // i.e. before it is created the database
    @PrePersist
    public void setCreatedAtAndLastModifiedAt() {
        LocalDateTime now = LocalDateTime.now();
        this.setCreatedAt(now);
        this.setLastModifiedAt(now);
        // TODO: lastModifiedBy
    }

    @PreUpdate
    public void setLastModifiedAt() {
        this.setLastModifiedAt(LocalDateTime.now());
        // TODO: lastModifiedBy
        // version handled by hibernate
    }
}
