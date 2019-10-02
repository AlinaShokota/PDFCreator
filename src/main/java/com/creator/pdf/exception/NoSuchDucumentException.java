package com.creator.pdf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchDucumentException extends RuntimeException implements Supplier<NoSuchDucumentException> {

    public NoSuchDucumentException() {
        super();
    }

    public NoSuchDucumentException(String message) {
        super(message);
    }


    @Override
    public NoSuchDucumentException get() {
        return new NoSuchDucumentException();
    }
}
