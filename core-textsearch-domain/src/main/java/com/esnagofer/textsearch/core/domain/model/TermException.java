package com.esnagofer.textsearch.core.domain.model;

public class TermException extends RuntimeException {

    public TermException() {
        super();
    }

    public TermException(String message) {
        super(message);
    }

    public TermException(String message, Throwable cause) {
        super(message, cause);
    }

    public TermException(Throwable cause) {
        super(cause);
    }

    protected TermException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
