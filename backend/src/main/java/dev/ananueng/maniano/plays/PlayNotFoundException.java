package dev.ananueng.maniano.plays;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayNotFoundException extends RuntimeException {
    public PlayNotFoundException() {
        super("Play Not Found");
    }
}
