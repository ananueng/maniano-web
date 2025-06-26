package dev.ananueng.maniano;

import dev.ananueng.maniano.file.FileRequestDto;
import dev.ananueng.maniano.file.FileService;
import dev.ananueng.maniano.file.FileType;
import dev.ananueng.maniano.song.SongRequestDto;
import dev.ananueng.maniano.song.SongService;
import dev.ananueng.maniano.plays.PlayRequestDto;
import dev.ananueng.maniano.plays.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger((Application.class));

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		log.info("Booted successfully!");
	}

	@Bean
	CommandLineRunner runner(SongService songService, PlayService playService, FileService fileService) {
		return args -> {
			// Songs
			songService.create(new SongRequestDto("First song", "init artist"));
			songService.create(new SongRequestDto("Second song", "init artist"));

			// Plays
			playService.create(new PlayRequestDto("First play", 1, null));
			playService.create(new PlayRequestDto("Second play", 1, null));

			// Files
			fileService.create(new FileRequestDto("test", 1, FileType.CORE_ENTITIES, null));
			fileService.create(new FileRequestDto("test2", 1, FileType.CORE_ENTITIES, null));
			fileService.create(new FileRequestDto("test3", 1, FileType.CONSTRAINTS, null));
		};
	}
}
