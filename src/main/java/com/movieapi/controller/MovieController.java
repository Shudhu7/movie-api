package com.movieapi.controller;

import com.movieapi.model.Movie;
import com.movieapi.model.SuccessResponse;
import com.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Movie API.
 * Handles CRUD operations for movies with success messages.
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    // Constructor injection
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Get all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // Get movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    // Add new movie with success message
    @PostMapping
    public ResponseEntity<SuccessResponse<Movie>> addMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.addMovie(movie);
        
        SuccessResponse<Movie> response = new SuccessResponse<>(
            HttpStatus.CREATED.value(),
            "Movie added successfully!",
            "The movie '" + createdMovie.getTitle() + "' has been added to the collection.",
            createdMovie
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update movie by ID with success message
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Movie>> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        
        SuccessResponse<Movie> response = new SuccessResponse<>(
            HttpStatus.OK.value(),
            "Movie updated successfully!",
            "The movie '" + updatedMovie.getTitle() + "' has been updated.",
            updatedMovie
        );
        
        return ResponseEntity.ok(response);
    }

    // Delete movie by ID with success message
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id); // Get movie before deleting to show title
        String movieTitle = movie.getTitle();
        movieService.deleteMovie(id);
        
        SuccessResponse<Void> response = new SuccessResponse<>(
            HttpStatus.OK.value(),
            "Movie deleted successfully!",
            "The movie '" + movieTitle + "' has been removed from the collection.",
            null
        );
        
        return ResponseEntity.ok(response);
    }
}