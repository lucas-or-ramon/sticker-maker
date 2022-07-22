package br.com.devcanoa.stickermaker.nasa;

import br.com.devcanoa.stickermaker.sticker.domain.Image;
import br.com.devcanoa.stickermaker.sticker.util.ContentExtractor;
import org.springframework.boot.json.JacksonJsonParser;

public class NasaContentExtractor implements ContentExtractor {

    @Override
    public Object extractJsonAsObject(String json) {
        return new JacksonJsonParser().parseList(json);
    }

    @Override
    public Image toImage(Object object) {
        return parseToMovie(object);
    }

    private Image parseToMovie(Object object) {
        return new Image(getAttribute("title", object), getAttribute("url", object));
    }

    private String getAttribute(String attribute, Object movie) {
        return movie.toString().split(attribute + "=")[1].split(",")[0];
    }
}
