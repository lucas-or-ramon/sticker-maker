package br.com.devcanoa.stickermaker.sticker.util;

import br.com.devcanoa.stickermaker.sticker.domain.Image;
import br.com.devcanoa.stickermaker.sticker.exception.JsonParserException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class JsonParser {
    private final ContentExtractor contentExtractor;

    public JsonParser(ContentExtractor contentExtractor) {
        this.contentExtractor = contentExtractor;
    }

    public List<Image> parse(String json) {
        try {
            return parseToImages(json);
        } catch (Exception e) {
            throw new JsonParserException("Erro ao transformar de Json para Imagem: " + e.getMessage());
        }
    }

    private List<Image> parseToImages(String json) {
        List<Image> images = new ArrayList<>();

        getInfoList(json).forEach(object -> images.add(contentExtractor.toImage(object)));

        return images;
    }

    private List<Object> getInfoList(String json) {
        return convertObjectToList(contentExtractor.extractJsonAsObject(json));
    }

    private List<Object> convertObjectToList(Object object) {
        return new ArrayList<>((Collection<?>) object);
    }

}
