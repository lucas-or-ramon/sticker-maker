package br.com.devcanoa.moviesticker.imdb;

import br.com.devcanoa.moviesticker.exception.ImdbClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

public class ImdbClient {
    private final String apiKey;

    public ImdbClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBody() {
        try {
            return Objects.requireNonNullElse(new RestTemplate().getForObject(getUri(), String.class), "");
        } catch (Exception e) {
            throw new ImdbClientException(e.getMessage());
        }
    }

    private URI getUri() {
        var url = "https://imdb-api.com/en/API/Top250Movies/";
        return URI.create(url + this.apiKey);
    }
}
