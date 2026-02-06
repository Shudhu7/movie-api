package com.movieapi.exception;

/**
 * Thrown when a movie is not found.
 * Handled by GlobalExceptionHandler.
 */

public class MovieNotFoundException extends RuntimeException {
    
    private final Long movieId;

    public MovieNotFoundException(Long movieId) {
        super(String.format("Movie not found with id: %d", movieId));
        this.movieId = movieId;
    }
    
    public MovieNotFoundException(String message) {
        super(message);
        this.movieId = null;
    }

    public Long getMovieId() {
        return movieId;
    }
}