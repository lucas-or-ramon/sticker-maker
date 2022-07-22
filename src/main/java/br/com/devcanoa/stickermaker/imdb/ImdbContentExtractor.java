package br.com.devcanoa.stickermaker.imdb;

import br.com.devcanoa.stickermaker.sticker.domain.Image;
import br.com.devcanoa.stickermaker.sticker.util.ContentExtractor;
import org.springframework.boot.json.JacksonJsonParser;

public class ImdbContentExtractor implements ContentExtractor {

    @Override
    public Object extractJsonAsObject(String json) {
        return new JacksonJsonParser().parseMap(json).get("items");
    }

    @Override
    public Image toImage(Object object) {
        return parseToMovie(object);
    }

    private Image parseToMovie(Object object) {
        return new Image(getAttribute("title", object), getAttribute("image", object));
    }

    private String getAttribute(String attribute, Object movie) {
        return movie.toString().split(attribute + "=")[1].split(",")[0];
    }
}
