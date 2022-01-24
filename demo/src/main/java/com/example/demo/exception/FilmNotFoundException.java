package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(Long id) {
        super("Could not find film " + id);
    }
}
