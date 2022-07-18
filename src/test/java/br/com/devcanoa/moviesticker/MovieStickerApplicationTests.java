package br.com.devcanoa.moviesticker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
class MovieStickerApplicationTests {
    protected String getStringWithTwoMovies() {
        try {
            return new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("twoMovies.txt")).readAllBytes());
        } catch (Exception e) {
            throw new IllegalArgumentException("File not found");
        }
    }
}
