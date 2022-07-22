package br.com.devcanoa.stickermaker.sticker.domain;

import br.com.devcanoa.stickermaker.controller.request.MessageRequest;

import java.awt.*;

public final class Message {
    private final Font font;
    private final Color color;
    private final String content;
    private final Position position;

    private Message(Font font, Color color, String content, Position position) {
        this.font = font;
        this.color = color;
        this.content = content;
        this.position = position;
    }

    public static Message of(MessageRequest messageRequest) {
        var font = new Font(fontNameValidator(messageRequest.fontName()), fontStyleValidator(messageRequest.fontStyle()), messageRequest.fontSize());
        var color  = colorValidator(messageRequest.color());

        return new Message(font, color , messageRequest.content(), messageRequest.position());
    }

    private static int fontStyleValidator(String style) {
        return switch (style.toUpperCase()) {
            case "PLAIN" -> Font.PLAIN;
            case "BOLD" -> Font.BOLD;
            case "ITALIC" -> Font.ITALIC;
            default -> throw new IllegalArgumentException("Erro ao obter estilo da fonte: " + style);
        };
    }

    private static String fontNameValidator(String name) {
        return switch (name.toUpperCase()) {
            case "SERIF" -> Font.SERIF;
            case "DIALOG" -> Font.DIALOG;
            case "SANS_SERIF" -> Font.SANS_SERIF;
            case "MONOSPACED" -> Font.MONOSPACED;
            case "DIALOG_INPUT" -> Font.DIALOG_INPUT;
            default -> throw new IllegalArgumentException("Erro ao obter nome da fonte: " + name);
        };
    }

    private static Color colorValidator(String color) {
        try {
            return (Color) Color.class.getField(color.toUpperCase()).get(null);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao obter a cor: " + e.getMessage());
        }
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }

    public String getContent() {
        return content;
    }

    public Position getPosition() {
        return position;
    }
}
