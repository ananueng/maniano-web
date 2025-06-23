package dev.ananueng.maniano.plays;

import dev.ananueng.maniano.file.File;
import dev.ananueng.maniano.file.FileMapper;
import dev.ananueng.maniano.file.FileNavResponseDto;
import dev.ananueng.maniano.file.FileType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayMapper {

    private final FileMapper fileMapper;

    public PlayMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public Play requestDtoToPlay(PlayRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Play play = new Play();
        play.setName(dto.name());
        play.setDueDate(dto.dueDate());
        return play;
    }

    public PlayRequestDto playToRequestDto(Play play) {
        if (play == null) {
            return null;
        }

        return new PlayRequestDto(
                play.getName(),
                play.getSongId(),
                play.getDueDate()
        );
    }

    public PlayResponseDto playToResponseDto(Play play) {
        if (play == null) {
            return null;
        }

        return new PlayResponseDto(
                play.getId(),
                play.getName(),
                play.getSongId(),
                play.getDueDate(),
                play.getCreatedAt(),
                play.getLastModifiedBy(),
                play.getLastModifiedAt(),
                play.getFiles().stream()
                        .map(fileMapper::fileToResponseDto)
                        .collect(Collectors.toList())
        );
    }

    // public PlayTableTypeSubNavResponseDto playToTableTypeNavResponseDto(Play play, FileType tableType) {
    //     if (play == null) {
    //         return null;
    //     }
    //
    //     return new PlayTableTypeSubNavResponseDto(
    //             play.getFiles().stream()
    //                     .filter(file -> file.getFileType() == tableType)
    //                     .map(fileToNavResponseDto)
    //                     .collect(Collectors.toList())
    //     );
    // }

    public PlayNavDataDto playToNavDataDto(Play play) {
        if (play == null) {
            return null;
        }

        List<PlayNavItemDto> navItems = List.of(
                new PlayNavItemDto(1, "Dashboard", String.format("/song/%d/play/%d",
                        play.getSongId(), play.getId()), "ic-blog", List.of()),
                createNavItem(2, play, FileType.CORE_ENTITIES, "Core Entities", "ic-user"),
                createNavItem(3, play, FileType.CONSTRAINTS, "Constraints", "ic-disabled"),
                createNavItem(4, play, FileType.METRICS, "Metrics", "ic-analytics"),
                new PlayNavItemDto(5, "Settings", String.format("/song/%d/play/%d/settings",
                        play.getSongId(), play.getId()), "ic-blog", List.of())
        );

        return new PlayNavDataDto(navItems);
    }

    private PlayNavItemDto createNavItem(Integer id, Play play, FileType tableType, String title, String icon) {
        List<FileNavResponseDto> subNavData = play.getFiles().stream()
                .filter(file -> file.getFileType() == tableType)
                .map(file -> fileToNavResponseDto(file, play.getSongId()))
                .collect(Collectors.toList());

        return new PlayNavItemDto(
                id,
                title,
                String.format("/song/%d/play/%d/%s",
                        play.getSongId(), play.getId(), tableType.name().toLowerCase()),
                icon, subNavData);
    }

    public FileNavResponseDto fileToNavResponseDto(File file, Integer songId) {
        if (file == null) {
            return null;
        }
        return new FileNavResponseDto(
                file.getId(),
                file.getName(),
                "/song/" + songId + "/play/" + file.getPlayId() + "/file/" + file.getId()
        );
    }
}