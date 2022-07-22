package br.com.devcanoa.stickermaker.nasa;

import br.com.devcanoa.stickermaker.controller.request.MessageRequest;
import br.com.devcanoa.stickermaker.sticker.client.ImageClient;
import br.com.devcanoa.stickermaker.sticker.domain.Image;
import br.com.devcanoa.stickermaker.sticker.domain.Message;
import br.com.devcanoa.stickermaker.sticker.domain.Sticker;
import br.com.devcanoa.stickermaker.sticker.exception.ImageNotFoundException;
import br.com.devcanoa.stickermaker.sticker.service.StickerService;
import br.com.devcanoa.stickermaker.sticker.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class NasaService {
    private final JsonParser jsonParser;
    private final ImageClient imageClient;
    private final StickerService stickerService;

    @Autowired
    public NasaService(@Qualifier("nasaJsonParser") JsonParser jsonParser,
                       @Qualifier("nasaImageClient") ImageClient imageClient,
                       StickerService stickerService) {
        this.jsonParser = jsonParser;
        this.imageClient = imageClient;
        this.stickerService = stickerService;
    }

    public Sticker getStickerByNasaImageTitle(MessageRequest messageRequest, String title) {
        var optionalMovie = getNasaImages().stream().filter(nasaImage -> nasaImage.title().contains(title)).findFirst();
        var nasaImage = optionalMovie.orElseThrow(() -> new ImageNotFoundException("Imagem da Nasa não encontrada com o título: " + title));

        return stickerService.getSticker(Message.of(messageRequest), nasaImage);
    }

    private List<Image> getNasaImages() {
        return jsonParser.parse(callNasaApi());
    }

    private String callNasaApi() {
        return imageClient.getBody(getUri());
    }

    private URI getUri() {
        return URI.create(System.getenv("nasaUrl"));
    }
}
