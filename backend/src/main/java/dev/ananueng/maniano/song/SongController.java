package dev.ananueng.maniano.song;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
class SongController {

    private final SongService songService;

    SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    List<SongResponseDto> findAll() {
        return songService.findAll();
    }

    @GetMapping("/{id}")
    SongResponseDto findById(@PathVariable Integer id) {
        return songService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody SongRequestDto songRequestDto) {
        songService.create(songRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    void patch(@Valid @RequestBody SongRequestDto songRequestDto, @PathVariable Integer id) {
        songService.patch(songRequestDto, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        songService.delete(id);
    }

    @GetMapping("/songName/{songName}")
    List<SongResponseDto> findByLocation(@PathVariable String songName) {
        return songService.findBySongTitle(songName);
    }
}