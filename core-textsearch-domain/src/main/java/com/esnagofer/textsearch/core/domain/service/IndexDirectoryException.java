package com.esnagofer.textsearch.core.domain.service;

public class IndexDirectoryException extends RuntimeException {

    public IndexDirectoryException() {
        super();
    }

    public IndexDirectoryException(String message) {
        super(message);
    }

    public IndexDirectoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexDirectoryException(Throwable cause) {
        super(cause);
    }

    protected IndexDirectoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
