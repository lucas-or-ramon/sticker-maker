package br.com.devcanoa.stickermaker.controller.request;

import br.com.devcanoa.stickermaker.sticker.domain.Position;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MessageRequest(@NotNull String color, @NotNull String content, @NotNull Position position,
                             @NotNull @Min(0L) Integer fontSize, @NotNull String fontName, @NotNull String fontStyle) {}
