package br.com.devcanoa.moviesticker.exception;

public class ImdbClientException extends RuntimeException {
    public ImdbClientException(String message) {
        super(message);
    }
}
