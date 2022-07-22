package br.com.devcanoa.stickermaker.nasa;

import br.com.devcanoa.stickermaker.sticker.client.ImageClient;
import br.com.devcanoa.stickermaker.sticker.util.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NasaConfig {

    @Bean("nasaImageClient")
    public ImageClient nasaRestTemplateProducer() {
        return new ImageClient(new RestTemplate());
    }

    @Bean("nasaJsonParser")
    public JsonParser nasaJsonParserProducer() {
        return new JsonParser(new NasaContentExtractor());
    }
}
