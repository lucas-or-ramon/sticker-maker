package br.com.devcanoa.moviesticker.exception;

public class ImdbJsonParserException extends RuntimeException {
    public ImdbJsonParserException(String message) {
        super(message);
    }
}
