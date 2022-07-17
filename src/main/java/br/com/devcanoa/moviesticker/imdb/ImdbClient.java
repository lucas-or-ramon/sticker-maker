package br.com.devcanoa.moviesticker.imdb;

import br.com.devcanoa.moviesticker.exception.ImdbClientException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
public class ImdbClient {

    public String getBody() {
        try {
            return Objects.requireNonNullElse(new RestTemplate().getForObject(getUri(), String.class), "");
        } catch (Exception e) {
            throw new ImdbClientException(e.getMessage());
        }
    }

    private URI getUri() {
        var apiKey = System.getenv("apiKey");
        var url = "https://imdb-api.com/en/API/Top250Movies/";
        return URI.create(url + apiKey);
    }
}
