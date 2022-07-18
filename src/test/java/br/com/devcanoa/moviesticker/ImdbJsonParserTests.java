package br.com.devcanoa.moviesticker;

import br.com.devcanoa.moviesticker.exception.ImdbJsonParserException;
import br.com.devcanoa.moviesticker.imdb.ImdbJsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImdbJsonParserTests extends MovieStickerApplicationTests {

    private final ImdbJsonParser imdbJsonParser = new ImdbJsonParser();

    @Test
    void testThrowsExceptionBecauseJsonStringIsInvalid() {
        assertThrows(ImdbJsonParserException.class, () -> imdbJsonParser.parse(""), "Deve Lançar exceção devido string json ser inválida");
    }

    @Test
    void testParseMovies() {
        var movies = imdbJsonParser.parse(getStringWithTwoMovies());
        assertEquals("The Shawshank Redemption", movies.get(0).title(), "Título do primeiro filme deve ser igual");
        assertEquals("The Godfather", movies.get(1).title(), "Título do segundo filme deve ser igual");
    }

    @Test
    void testParseTwoMovies() {
        assertEquals(imdbJsonParser.parse(getStringWithTwoMovies()).size(), 2, "Deve retornar lista com 2 filmes");
    }
}
