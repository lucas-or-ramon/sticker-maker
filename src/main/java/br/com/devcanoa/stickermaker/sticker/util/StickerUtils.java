package br.com.devcanoa.stickermaker.sticker.util;

import br.com.devcanoa.stickermaker.sticker.domain.File;

import java.net.MalformedURLException;
import java.net.URL;

public final class StickerUtils {
    private StickerUtils() {}

    public static URL generatePublicUrl(File file) {
        try {
            return new URL(String.format("https://%s.s3.us-east-1.amazonaws.com/%s", file.getBucketName(), file.getFullPath()));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Erro ao gerar url pública do arquivo: " + e.getMessage());
        }
    }
}
