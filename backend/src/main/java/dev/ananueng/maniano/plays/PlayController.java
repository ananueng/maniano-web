package dev.ananueng.maniano.plays;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plays")
class PlayController {

    private final PlayService playService;

    PlayController(PlayService playService) {
        this.playService = playService;
    }

    @GetMapping
    List<PlayResponseDto> findAll() {
        return playService.findAll();
    }

    @GetMapping("/{id}")
    PlayResponseDto findById(@PathVariable Integer id) {
        return playService.findExistingById(id);
    }

    // @GetMapping("/{id}/files/type/{tableType}")
    // PlayTableTypeSubNavResponseDto findFilesTypeById(@PathVariable Integer id, @PathVariable String tableType) {
    //     return playService.findFilesByIdAndType(id, tableType);
    // }

    @GetMapping("/{id}/nav")
    PlayNavDataDto findNavDataByIdById(@PathVariable Integer id) {
        return playService.findNavDataById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody PlayRequestDto playRequestDto) {
        playService.create(playRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    void patch(@Valid @RequestBody PlayRequestDto playRequestDto, @PathVariable Integer id) {
        playService.patch(playRequestDto, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        playService.delete(id);
    }
}