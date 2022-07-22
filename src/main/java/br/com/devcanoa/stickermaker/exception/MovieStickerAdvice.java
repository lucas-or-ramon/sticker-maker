package br.com.devcanoa.stickermaker.exception;

import br.com.devcanoa.stickermaker.sticker.exception.ImageNotFoundException;
import br.com.devcanoa.stickermaker.sticker.exception.StickerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieStickerAdvice {

    @ExceptionHandler(value = {ImageNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleImdbException(ImageNotFoundException ex) {
        var httpStatus = HttpStatus.NOT_FOUND;
        var errorMessage = new ErrorMessage(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = StickerException.class)
    public ResponseEntity<ErrorMessage> handleStickerException(StickerException ex) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorMessage = new ErrorMessage(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentsException(IllegalArgumentException ex) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorMessage = new ErrorMessage(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
