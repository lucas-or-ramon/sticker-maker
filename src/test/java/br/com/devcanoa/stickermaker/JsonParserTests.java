package br.com.devcanoa.stickermaker;

import br.com.devcanoa.stickermaker.sticker.exception.JsonParserException;
import br.com.devcanoa.stickermaker.sticker.util.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonParserTests extends MovieStickerApplicationTests {

    private final JsonParser jsonParser = new JsonParser(null);

    @Test
    void testThrowsExceptionBecauseJsonStringIsInvalid() {
        assertThrows(JsonParserException.class, () -> jsonParser.parse(""), "Deve Lançar exceção devido string json ser inválida");
    }

    @Test
    void testParseMovies() {
        var movies = jsonParser.parse(getStringWithTwoMovies());
        assertEquals("The Shawshank Redemption", movies.get(0).title(), "Título do primeiro filme deve ser igual");
        assertEquals("The Godfather", movies.get(1).title(), "Título do segundo filme deve ser igual");
    }

    @Test
    void testParseTwoMovies() {
        assertEquals(jsonParser.parse(getStringWithTwoMovies()).size(), 2, "Deve retornar lista com 2 filmes");
    }
}
