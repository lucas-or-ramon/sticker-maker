package br.com.devcanoa.moviesticker.exception;

import org.springframework.http.HttpStatus;

public record ErrorMessage(String message, HttpStatus statusCode) {}
