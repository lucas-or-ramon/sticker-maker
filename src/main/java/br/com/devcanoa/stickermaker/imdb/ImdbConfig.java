package br.com.devcanoa.stickermaker.imdb;

import br.com.devcanoa.stickermaker.sticker.client.ImageClient;
import br.com.devcanoa.stickermaker.sticker.util.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ImdbConfig {

    @Bean("imdbImageClient")
    public ImageClient imdbRestTemplateProducer() {
        return new ImageClient(new RestTemplate());
    }

    @Bean("imdbJsonParser")
    public JsonParser imdbJsonParserProducer() {
        return new JsonParser(new ImdbContentExtractor());
    }
}
