package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(Long id) {
        super("Could not find actor with id = " + id);
    }
}
