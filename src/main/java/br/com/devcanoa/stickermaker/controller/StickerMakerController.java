package br.com.devcanoa.stickermaker.controller;

import br.com.devcanoa.stickermaker.controller.request.MessageRequest;
import br.com.devcanoa.stickermaker.controller.response.StickerResponse;
import br.com.devcanoa.stickermaker.imdb.ImdbService;
import br.com.devcanoa.stickermaker.nasa.NasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/sticker")
public class StickerMakerController {
    private final ImdbService imdbService;
    private final NasaService nasaService;

    @Autowired
    public StickerMakerController(ImdbService imdbService, NasaService nasaService) {
        this.imdbService = imdbService;
        this.nasaService = nasaService;
    }

    @PostMapping(value = "/imdb")
    public ResponseEntity<StickerResponse> getStickerByMovieTitle(@RequestBody MessageRequest messageRequest,
                                                                  @RequestParam String title) {
        return ResponseEntity.ok(StickerResponse.of(imdbService.getStickerByTitleMovie(messageRequest, title)));
    }

    @PostMapping(value = "/nasa")
    public ResponseEntity<StickerResponse> getStickerByNasaImageTitle(@RequestBody MessageRequest messageRequest,
                                                                      @RequestParam String title) {
        return ResponseEntity.ok(StickerResponse.of(nasaService.getStickerByNasaImageTitle(messageRequest, title)));
    }

}
