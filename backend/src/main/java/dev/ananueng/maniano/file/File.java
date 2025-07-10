package dev.ananueng.maniano.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.ananueng.maniano.plays.Play;
import dev.ananueng.maniano.utilities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@SequenceGenerator(name = "seq_gen", sequenceName = "file_seq", allocationSize = 1)
@SQLRestriction("deleted <> true")
public class File extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    FileType fileType;

    @Enumerated(EnumType.STRING)
    FileStatus fileStatus;

    @Version
    private Integer version;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "play_id", nullable = true)
    private Play play;

    // @JsonProperty("fileId")
    public Integer getPlayId() {
        return play.getId();
    }
}
