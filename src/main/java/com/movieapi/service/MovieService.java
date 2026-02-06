package com.movieapi.service;

import com.movieapi.exception.MovieNotFoundException;
import com.movieapi.exception.MovieValidationException;
import com.movieapi.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class that manages the in-memory data store for movies.
 * Uses ArrayList to store movie objects and provides CRUD operations.
 */
@Service
public class MovieService {
    
    // In-memory data store using ArrayList
    private final List<Movie> movieStore = new ArrayList<>();
    
    // Auto-incrementing ID generator
    private final AtomicLong idCounter = new AtomicLong(1);

    /**
     * Constructor that initializes the store with some sample movies
     */
    public MovieService() {
        // Pre-populate with sample data
        addMovie(new Movie(null, "The Shawshank Redemption", 
            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            "Drama", 1994, 9.3));
        
        addMovie(new Movie(null, "Inception", 
            "A thief who steals corporate secrets through the use of dream-sharing technology.",
            "Sci-Fi", 2010, 8.8));
        
        addMovie(new Movie(null, "The Dark Knight", 
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham.",
            "Action", 2008, 9.0));
    }

    /**
     * Adds a new movie to the collection
     * @param movie The movie to add (id will be auto-generated)
     * @return The added movie with generated ID
     * @throws MovieValidationException if validation fails
     */
    public Movie addMovie(Movie movie) {
        validateMovie(movie);
        movie.setId(idCounter.getAndIncrement());
        movieStore.add(movie);
        return movie;
    }

    /**
     * Retrieves a movie by its ID
     * @param id The ID of the movie to retrieve
     * @return The movie if found
     * @throws MovieNotFoundException if movie is not found
     */
    public Movie getMovieById(Long id) {
        return movieStore.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    /**
     * Retrieves all movies in the collection
     * @return List of all movies
     */
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieStore);
    }

    /**
     * Updates an existing movie
     * @param id The ID of the movie to update
     * @param updatedMovie The movie object with updated information
     * @return The updated movie
     * @throws MovieNotFoundException if movie is not found
     * @throws MovieValidationException if validation fails
     */
    public Movie updateMovie(Long id, Movie updatedMovie) {
        validateMovie(updatedMovie);
        
        for (int i = 0; i < movieStore.size(); i++) {
            if (movieStore.get(i).getId().equals(id)) {
                updatedMovie.setId(id);
                movieStore.set(i, updatedMovie);
                return updatedMovie;
            }
        }
        throw new MovieNotFoundException(id);
    }

    /**
     * Deletes a movie by its ID
     * @param id The ID of the movie to delete
     * @throws MovieNotFoundException if movie is not found
     */
    public void deleteMovie(Long id) {
        boolean deleted = movieStore.removeIf(movie -> movie.getId().equals(id));
        if (!deleted) {
            throw new MovieNotFoundException(id);
        }
    }

    /**
     * Validates movie data
     * @param movie The movie to validate
     * @throws MovieValidationException if validation fails
     */
    private void validateMovie(Movie movie) {
        // Validate title
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            throw new MovieValidationException("title", "Title is required and cannot be empty");
        }

        // Validate rating
        if (movie.getRating() != null && (movie.getRating() < 0 || movie.getRating() > 10)) {
            throw new MovieValidationException("rating", "Rating must be between 0 and 10");
        }

        // Validate release year
        if (movie.getReleaseYear() != null && (movie.getReleaseYear() < 1888 || movie.getReleaseYear() > 2100)) {
            throw new MovieValidationException("releaseYear", 
                "Release year must be between 1888 (first movie ever made) and 2100");
        }
    }
}