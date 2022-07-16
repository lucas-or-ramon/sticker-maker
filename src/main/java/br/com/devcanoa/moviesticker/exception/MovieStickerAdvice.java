package br.com.devcanoa.moviesticker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieStickerAdvice {

    @ExceptionHandler(value = ImdbClientException.class)
    public ResponseEntity<ErrorMessage> handleImdbClientException(ImdbClientException ex) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorMessage = new ErrorMessage(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(value = ImdbJsonParserException.class)
    public ResponseEntity<ErrorMessage> handleImdbJsonParserException(ImdbJsonParserException ex) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorMessage = new ErrorMessage(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
