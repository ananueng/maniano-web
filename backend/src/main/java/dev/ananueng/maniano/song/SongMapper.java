package dev.ananueng.maniano.song;

import dev.ananueng.maniano.plays.PlayMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SongMapper {

    private final PlayMapper playMapper;

    public SongMapper(PlayMapper playMapper) {
        this.playMapper = playMapper;
    }

    public Song requestDtoToSong(SongRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Song song = new Song();
        song.setTitle(dto.title());
        song.setDescription(dto.description());
        return song;
    }

    public SongRequestDto songToRequestDto(Song song) {
        if (song == null) {
            return null;
        }

        return new SongRequestDto(
                song.getTitle(),
                song.getDescription()
        );
    }

    public SongResponseDto songToResponseDto(Song song) {
        if (song == null) {
            return null;
        }

        return new SongResponseDto(
                song.getId(),
                song.getTitle(),
                song.getDescription(),
                song.getCreatedBy(),
                song.getCreatedAt(),
                song.getLastModifiedBy(),
                song.getLastModifiedAt(),
                "/song/" + song.getId(),
                song.getPlays().stream()
                        .map(playMapper::playToResponseDto)
                        .collect(Collectors.toList())
        );
    }
}