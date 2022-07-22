package br.com.devcanoa.stickermaker.controller.response;

import br.com.devcanoa.stickermaker.sticker.domain.Sticker;

public record StickerResponse(String fileName, String publicUrl) {
    public static StickerResponse of(Sticker sticker) {
        return new StickerResponse(sticker.getFileName(), sticker.getPublicUrl().toString());
    }
}
