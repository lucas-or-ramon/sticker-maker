package br.com.devcanoa.moviesticker.controller;

import br.com.devcanoa.moviesticker.domain.Movie;
import br.com.devcanoa.moviesticker.imdb.ImdbClient;
import br.com.devcanoa.moviesticker.imdb.ImdbJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sticker")
public class MovieStickerController {

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        var json = new ImdbClient("k_j9jmszw8").getBody();
        var movies = new ImdbJsonParser(json).parse();

        return ResponseEntity.ok(movies);
    }
}
