package dev.ananueng.maniano.file;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
class FileController {

    private final FileService fileService;

    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    List<FileResponseDto> findAll() {
        return fileService.findAll();
    }

    @GetMapping("/{id}")
    FileResponseDto findById(@PathVariable Integer id) {
        return fileService.findExistingById(id);
    }

    @GetMapping("/type/{type}")
    List<FileResponseDto> findAllByType(@PathVariable String type) {
        return fileService.findAllByType(type);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody FileRequestDto fileRequestDto) {
        fileService.create(fileRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    void patch(@Valid @RequestBody FileRequestDto fileRequestDto, @PathVariable Integer id) {
        fileService.patch(fileRequestDto, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        fileService.delete(id);
    }
}