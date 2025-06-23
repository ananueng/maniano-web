package dev.ananueng.maniano.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findAllByFileType(FileType type);
}