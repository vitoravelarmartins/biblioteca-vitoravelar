package com.vitor.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ThereIsNot extends RuntimeException {
    public ThereIsNot(String message){
        super(message);
    }
}
