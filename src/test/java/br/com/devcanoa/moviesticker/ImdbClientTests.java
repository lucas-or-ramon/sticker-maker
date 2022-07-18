package br.com.devcanoa.moviesticker;

import br.com.devcanoa.moviesticker.exception.ImdbClientException;
import br.com.devcanoa.moviesticker.imdb.ImdbClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImdbClientTests extends MovieStickerApplicationTests {

    private ImdbClient imdbClient;
    private RestTemplate restTemplate;

    @BeforeEach
    void setup() {
        this.restTemplate = createMock(RestTemplate.class);
        this.imdbClient = new ImdbClient(restTemplate);
    }

    @Test
    void testThrowExceptionBecauseErrorToConnectImdbAPI() {
        expect(restTemplate.getForObject(anyObject(), same(String.class))).andThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        replay(restTemplate);

        assertThrows(ImdbClientException.class, () -> imdbClient.getBody(), "Deve lançar exceção devido erro ao conectar à API do imdb");
    }

    @Test
    void testEmptyStringBecauseImdbResponseIsNull() {
        expect(restTemplate.getForObject(anyObject(), same(String.class))).andReturn(null);
        replay(restTemplate);

        assertEquals(imdbClient.getBody(), "", "Deve retornar string vazia devido resposta da API do imdb ser null");
    }

    @Test
    void testCompleteJsonBecauseImdbResponseIsValid() {
        expect(restTemplate.getForObject(anyObject(), same(String.class))).andReturn(getStringWithTwoMovies());
        replay(restTemplate);

        assertEquals(imdbClient.getBody(), getStringWithTwoMovies(), "Deve retornar a string json completa");
    }
}
