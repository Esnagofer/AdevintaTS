package com.esnagofer.textsearch.core.domain.service;

public class ParseFileException extends RuntimeException {

    public ParseFileException() {
        super();
    }

    public ParseFileException(String message) {
        super(message);
    }

    public ParseFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseFileException(Throwable cause) {
        super(cause);
    }

    protected ParseFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
