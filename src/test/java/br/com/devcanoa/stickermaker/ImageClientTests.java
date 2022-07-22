package br.com.devcanoa.stickermaker;

import br.com.devcanoa.stickermaker.sticker.exception.ImageClientException;
import br.com.devcanoa.stickermaker.sticker.client.ImageClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageClientTests extends MovieStickerApplicationTests {

    private ImageClient imageClient;
    private RestTemplate restTemplate;

    @BeforeEach
    void setup() {
        this.restTemplate = createMock(RestTemplate.class);
        this.imageClient = new ImageClient(restTemplate);
    }

    @Test
    void testThrowExceptionBecauseErrorToConnectImdbAPI() {
        expect(restTemplate.getForObject(anyObject(), same(String.class))).andThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        replay(restTemplate);

        assertThrows(ImageClientException.class, () -> imageClient.getBody(null), "Deve lançar exceção devido erro ao conectar à API do imdb");
    }

    @Test
    void testEmptyStringBecauseImdbResponseIsNull() {
        expect(restTemplate.getForObject(anyObject(), same(String.class))).andReturn(null);
        replay(restTemplate);

        assertEquals(imageClient.getBody(null), "", "Deve retornar string vazia devido resposta da API do imdb ser null");
    }

    @Test
    void testCompleteJsonBecauseImdbResponseIsValid() {
        expect(restTemplate.getForObject(anyObject(), same(String.class))).andReturn(getStringWithTwoMovies());
        replay(restTemplate);

        assertEquals(imageClient.getBody(null), getStringWithTwoMovies(), "Deve retornar a string json completa");
    }
}
