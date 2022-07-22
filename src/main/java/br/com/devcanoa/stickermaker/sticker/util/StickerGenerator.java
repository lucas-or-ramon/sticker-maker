package br.com.devcanoa.stickermaker.sticker.util;

import br.com.devcanoa.stickermaker.sticker.exception.StickerGeneratorException;
import br.com.devcanoa.stickermaker.sticker.domain.Image;
import br.com.devcanoa.stickermaker.sticker.domain.Message;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.awt.Transparency.TRANSLUCENT;

public final class StickerGenerator {

    private final Image image;
    private final Message message;

    public StickerGenerator(Message message, Image image) {
        this.image = image;
        this.message = message;
    }

    public byte[] generate() {
        try {
            return generateNewSticker();
        } catch (IOException e) {
            throw new StickerGeneratorException("Erro ao gerar sticker: " + e.getMessage());
        }
    }

    private byte[] generateNewSticker() throws IOException {
        var originalImage = ImageIO.read(new URL(image.urlImage()).openStream());

        BufferedImage newSticker = getSticker(originalImage);

        var outputStream = new ByteArrayOutputStream();
        ImageIO.write(newSticker, "png", outputStream);
        return outputStream.toByteArray();
    }

    private BufferedImage getSticker(BufferedImage originalImage) {
        var width = originalImage.getWidth();
        var height = originalImage.getHeight();
        var increase = (int) (height * 0.2);

        var newImage = new BufferedImage(width, height + increase, TRANSLUCENT);

        var graphics2D = (Graphics2D) newImage.getGraphics();
        graphics2D.drawImage(originalImage, 0, 0, null);

        int x = (int) (width * 0.2);
        int y = height + (increase / 2);

        drawMessageOnSticker(graphics2D, x, y);

        return newImage;
    }

    private void drawMessageOnSticker(Graphics2D graphics2D, int x, int y) {
        graphics2D.setFont(message.getFont());
        graphics2D.setColor(message.getColor());
        graphics2D.drawString(message.getContent(), x, y);
    }
}
