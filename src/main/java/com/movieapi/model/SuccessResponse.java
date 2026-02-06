package com.movieapi.model;

import java.time.LocalDateTime;

/**
 * Standardized success response model for API operations.
 * This provides a consistent success message format across all endpoints.
 */
public class SuccessResponse<T> {
    
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String details;
    private T data;

    public SuccessResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public SuccessResponse(int status, String message, String details, T data) {
        this();
        this.status = status;
        this.message = message;
        this.details = details;
        this.data = data;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}