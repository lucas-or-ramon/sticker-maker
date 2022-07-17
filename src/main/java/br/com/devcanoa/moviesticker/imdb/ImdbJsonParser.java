package br.com.devcanoa.moviesticker.imdb;

import br.com.devcanoa.moviesticker.domain.Movie;
import br.com.devcanoa.moviesticker.exception.ImdbJsonParserException;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ImdbJsonParser {

    public List<Movie> parse(String json) {
        try {
            return parseMovies(json);
        } catch (Exception e) {
            throw new ImdbJsonParserException(e.getMessage());
        }
    }

    private List<Movie> parseMovies(String json) {
        List<Movie> movies = new ArrayList<>();

        getMoviesObjectsFromJson(json).forEach(object -> movies.add(parseMovie(object)));

        return movies;
    }

    private List<Object> getMoviesObjectsFromJson(String json) {
        return convertObjectToList(new JacksonJsonParser().parseMap(json).get("items"));
    }

    private List<Object> convertObjectToList(Object object) {
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
