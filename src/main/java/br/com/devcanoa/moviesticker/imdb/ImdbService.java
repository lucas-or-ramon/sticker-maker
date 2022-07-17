package br.com.devcanoa.moviesticker.imdb;

import br.com.devcanoa.moviesticker.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImdbService {

    private final ImdbClient imdbClient;
    private final ImdbJsonParser imdbJsonParser;

    @Autowired
    public ImdbService(ImdbClient imdbClient, ImdbJsonParser imdbJsonParser) {
        this.imdbClient = imdbClient;
        this.imdbJsonParser = imdbJsonParser;
    }

    public List<Movie> getTop250Movies() {
        return imdbJsonParser.parse(imdbClient.getBody());
    }
}
