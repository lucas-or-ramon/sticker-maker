package br.com.devcanoa.stickermaker.sticker.util;

import br.com.devcanoa.stickermaker.sticker.domain.Image;

public interface ContentExtractor {
    Object extractJsonAsObject(String json);
    Image toImage(Object object);
}
