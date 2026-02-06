package com.movieapi.exception;

/**
 * Custom exception thrown when movie validation fails.
 * This exception is handled globally by the GlobalExceptionHandler.
 */
public class MovieValidationException extends RuntimeException {
    
    private final String field;

    public MovieValidationException(String message) {
        super(message);
        this.field = null;
    }

    public MovieValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}