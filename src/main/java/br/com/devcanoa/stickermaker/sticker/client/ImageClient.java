package br.com.devcanoa.stickermaker.sticker.client;

import br.com.devcanoa.stickermaker.sticker.exception.ImageClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

public class ImageClient {

    private final RestTemplate restTemplate;

    public ImageClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBody(URI uri) {
        try {
            return Objects.requireNonNullElse(restTemplate.getForObject(uri, String.class), "");
        } catch (Exception e) {
            throw new ImageClientException("Erro ao buscar imagens: " + e.getMessage());
        }
    }
}
