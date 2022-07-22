package br.com.devcanoa.stickermaker.exception;

import org.springframework.http.HttpStatus;

public record ErrorMessage(String message, HttpStatus statusCode) {}
