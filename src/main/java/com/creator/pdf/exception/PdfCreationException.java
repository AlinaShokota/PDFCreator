package com.creator.pdf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PdfCreationException extends RuntimeException {

    public PdfCreationException(String message) {
        super(message);
    }
}
