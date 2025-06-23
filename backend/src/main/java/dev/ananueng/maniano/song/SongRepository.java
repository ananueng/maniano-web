package dev.ananueng.maniano.song;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findAllByTitle(String title);
}