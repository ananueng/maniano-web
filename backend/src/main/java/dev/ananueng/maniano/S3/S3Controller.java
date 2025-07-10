package dev.ananueng.maniano.S3;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {
    private final S3Service s3Service;

    S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/download/{fileKey}")
    String getFileDownloadUrl(@PathVariable String fileKey) {
        return s3Service.getPresignedUrl(fileKey);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    void uploadFile(@RequestParam MultipartFile file) {
        s3Service.uploadFile(file);
    }
}
