package br.com.devcanoa.moviesticker.imdb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ImdbConfig {

    @Bean("restTemplate")
    public RestTemplate restTemplateProducer() {
        return new RestTemplate();
    }
}
