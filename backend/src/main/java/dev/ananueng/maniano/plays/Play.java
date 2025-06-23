package dev.ananueng.maniano.plays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.ananueng.maniano.file.File;
import dev.ananueng.maniano.utilities.BaseEntity;
import dev.ananueng.maniano.song.Song;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@SequenceGenerator(name = "seq_gen", sequenceName = "play_seq", allocationSize = 1)
@SQLRestriction("deleted <> true")
public class Play extends BaseEntity {

    private String name;
    private LocalDateTime dueDate;

    @Version
    private Integer version;

    // song_id is what this field should be named in the database (snake case)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @JsonIgnore
    public Integer getSongId() {
        return song.getId();
    }

    // refers to the attribute value of the **Java** object that this maniano to (i.e. File.play), not the database value
    @OneToMany(mappedBy = "play", cascade = CascadeType.REMOVE)
    private List<File> files;
}
