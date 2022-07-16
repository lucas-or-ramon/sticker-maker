package br.com.devcanoa.moviesticker.imdb;

import br.com.devcanoa.moviesticker.domain.Movie;
import br.com.devcanoa.moviesticker.exception.ImdbJsonParserException;
import org.springframework.boot.json.JacksonJsonParser;

import java.util.*;

public class ImdbJsonParser {

    private final String json;

    public ImdbJsonParser(String json) {
        this.json = json;
    }

    public List<Movie> parse() {
        try {
            return parseMovies();
        } catch (Exception e) {
            throw new ImdbJsonParserException(e.getMessage());
        }
    }

    private List<Movie> parseMovies() {
        List<Movie> movies = new ArrayList<>();

        getMoviesObjectsFromJson().forEach(object -> movies.add(parseMovie(object)));

        return movies;
    }

    private List<Object> getMoviesObjectsFromJson() {
        return convertObjectToList(new JacksonJsonParser().parseMap(json).get("items"));
    }

    public List<Object> convertObjectToList(Object object) {
        return new ArrayList<>((Collection<?>) object);
    }

    private Movie parseMovie(Object object) {
        return new Movie(
                getAttribute("year", object),
                getAttribute("title", object),
                getAttribute("imDbRating", object),
                getAttribute("image", object));
    }

    private String getAttribute(String attribute, Object movie) {
        return movie.toString().split(attribute + "=")[1].split(",")[0];
    }
}
