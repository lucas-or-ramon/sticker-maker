package br.com.devcanoa.stickermaker.imdb;

import br.com.devcanoa.stickermaker.controller.request.MessageRequest;
import br.com.devcanoa.stickermaker.sticker.exception.ImageNotFoundException;
import br.com.devcanoa.stickermaker.sticker.client.ImageClient;
import br.com.devcanoa.stickermaker.sticker.domain.Image;
import br.com.devcanoa.stickermaker.sticker.domain.Message;
import br.com.devcanoa.stickermaker.sticker.domain.Sticker;
import br.com.devcanoa.stickermaker.sticker.service.StickerService;
import br.com.devcanoa.stickermaker.sticker.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class ImdbService {
    private final JsonParser jsonParser;
    private final ImageClient imageClient;
    private final StickerService stickerService;

    @Autowired
    public ImdbService(@Qualifier("imdbJsonParser") JsonParser jsonParser,
                       @Qualifier("imdbImageClient") ImageClient imageClient,
                       StickerService stickerService) {
        this.jsonParser = jsonParser;
        this.imageClient = imageClient;
        this.stickerService = stickerService;
    }

    public Sticker getStickerByTitleMovie(MessageRequest messageRequest, String title) {
        var optionalMovie = getTop250Movies().stream().filter(movie -> movie.title().contains(title)).findFirst();
        var movie = optionalMovie.orElseThrow(() -> new ImageNotFoundException("Filme não encontrado com o título: " + title));

        return stickerService.getSticker(Message.of(messageRequest), movie);
    }

    private List<Image> getTop250Movies() {
        return jsonParser.parse(callImdbApi());
    }

    private String callImdbApi() {
        return imageClient.getBody(getUri());
    }

    private URI getUri() {
        return URI.create(System.getenv("imdbUrl"));
    }
}
