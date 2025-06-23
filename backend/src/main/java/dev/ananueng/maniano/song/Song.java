package dev.ananueng.maniano.song;

import dev.ananueng.maniano.utilities.BaseEntity;
import dev.ananueng.maniano.plays.Play;
import jakarta.persistence.*;

import java.util.List;

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
@SequenceGenerator(name = "seq_gen", sequenceName = "song_seq", allocationSize = 1)
@SQLRestriction("deleted <> true")
public class Song extends BaseEntity {

    // @Enumerated(EnumType.STRING)
    // private SongType title;

    private String title;
    private String description;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "song", cascade = CascadeType.REMOVE)
    private List<Play> plays;
}
