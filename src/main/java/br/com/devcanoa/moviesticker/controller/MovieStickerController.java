package br.com.devcanoa.moviesticker.controller;

import br.com.devcanoa.moviesticker.domain.Movie;
import br.com.devcanoa.moviesticker.imdb.ImdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sticker")
public class MovieStickerController {

    private final ImdbService imdbService;

    @Autowired
    public MovieStickerController(ImdbService imdbService) {
        this.imdbService = imdbService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(imdbService.getTop250Movies());
    }
}
