package com.vitor.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CanNotDo extends RuntimeException{
    public CanNotDo(String message){
        super(message);
    }
}
