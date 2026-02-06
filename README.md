# 🎬 Movie API - Spring Boot REST API

A RESTful API for managing a movie collection built with Spring Boot. This API provides complete CRUD (Create, Read, Update, Delete) operations with robust error handling and success messaging.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Request & Response Examples](#request--response-examples)
- [Error Handling](#error-handling)
- [Validation Rules](#validation-rules)
- [Contributing](#contributing)

## ✨ Features

- ✅ Full CRUD operations for movies
- ✅ In-memory data storage using ArrayList
- ✅ Comprehensive error handling with custom exceptions
- ✅ Success messages for all operations
- ✅ Input validation with detailed error responses
- ✅ Auto-incrementing IDs
- ✅ Pre-populated sample data
- ✅ RESTful API design principles
- ✅ Consistent response format

## 🛠 Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Web**
- **Maven**

## 📁 Project Structure

```
src/main/java/com/movieapi/
│
├── MovieApiApplication.java          # Main application entry point
│
├── controller/
│   └── MovieController.java          # REST endpoints
│
├── service/
│   └── MovieService.java             # Business logic & data management
│
├── model/
│   ├── Movie.java                    # Movie entity
│   ├── ErrorResponse.java            # Error response model
│   └── SuccessResponse.java          # Success response model
│
└── exception/
    ├── GlobalExceptionHandler.java   # Centralized exception handling
    ├── MovieNotFoundException.java   # Custom 404 exception
    └── MovieValidationException.java # Custom validation exception
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Shudhu7/movie-api.git
   cd movie-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   ```
   The API will be available at: http://localhost:8080
   ```

## 🔌 API Endpoints

### Base URL
```
http://localhost:8080/api/movies
```

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/movies` | Get all movies |
| GET    | `/api/movies/{id}` | Get movie by ID |
| POST   | `/api/movies` | Add a new movie |
| PUT    | `/api/movies/{id}` | Update a movie |
| DELETE | `/api/movies/{id}` | Delete a movie |

## 📝 Request & Response Examples

### 1. Get All Movies

**Request:**
```http
GET /api/movies
```

**Response:**
```json
[
  {
    "id": 1,
    "title": "The Shawshank Redemption",
    "description": "Two imprisoned men bond over a number of years...",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.3
  },
  {
    "id": 2,
    "title": "Inception",
    "description": "A thief who steals corporate secrets...",
    "genre": "Sci-Fi",
    "releaseYear": 2010,
    "rating": 8.8
  }
]
```

### 2. Get Movie by ID

**Request:**
```http
GET /api/movies/1
```

**Response:**
```json
{
  "id": 1,
  "title": "The Shawshank Redemption",
  "description": "Two imprisoned men bond over a number of years...",
  "genre": "Drama",
  "releaseYear": 1994,
  "rating": 9.3
}
```

### 3. Add New Movie

**Request:**
```http
POST /api/movies
Content-Type: application/json

{
  "title": "The Matrix",
  "description": "A computer hacker learns about the true nature of reality.",
  "genre": "Sci-Fi",
  "releaseYear": 1999,
  "rating": 8.7
}
```

**Success Response:**
```json
{
  "timestamp": "2026-02-06T10:30:00",
  "status": 201,
  "message": "Movie added successfully!",
  "details": "The movie 'The Matrix' has been added to the collection.",
  "data": {
    "id": 4,
    "title": "The Matrix",
    "description": "A computer hacker learns about the true nature of reality.",
    "genre": "Sci-Fi",
    "releaseYear": 1999,
    "rating": 8.7
  }
}
```

### 4. Update Movie

**Request:**
```http
PUT /api/movies/4
Content-Type: application/json

{
  "title": "The Matrix",
  "description": "A computer hacker learns about the true nature of reality and his role in the war against its controllers.",
  "genre": "Sci-Fi",
  "releaseYear": 1999,
  "rating": 8.8
}
```

**Success Response:**
```json
{
  "timestamp": "2026-02-06T10:35:00",
  "status": 200,
  "message": "Movie updated successfully!",
  "details": "The movie 'The Matrix' has been updated.",
  "data": {
    "id": 4,
    "title": "The Matrix",
    "description": "A computer hacker learns about the true nature of reality and his role in the war against its controllers.",
    "genre": "Sci-Fi",
    "releaseYear": 1999,
    "rating": 8.8
  }
}
```

### 5. Delete Movie

**Request:**
```http
DELETE /api/movies/4
```

**Success Response:**
```json
{
  "timestamp": "2026-02-06T10:40:00",
  "status": 200,
  "message": "Movie deleted successfully!",
  "details": "The movie 'The Matrix' has been removed from the collection.",
  "data": null
}
```

## ❌ Error Handling

The API provides detailed error responses for various scenarios:

### 1. Movie Not Found (404)

```json
{
  "timestamp": "2026-02-06T10:45:00",
  "status": 404,
  "error": "Not Found",
  "message": "Movie not found with id: 999",
  "path": "/api/movies/999",
  "validationErrors": []
}
```

### 2. Validation Error (400)

```json
{
  "timestamp": "2026-02-06T10:50:00",
  "status": 400,
  "error": "Validation Error",
  "message": "Rating must be between 0 and 10",
  "path": "/api/movies",
  "validationErrors": [
    {
      "field": "rating",
      "message": "Rating must be between 0 and 10"
    }
  ]
}
```

### 3. Invalid JSON Format (400)

```json
{
  "timestamp": "2026-02-06T10:55:00",
  "status": 400,
  "error": "Malformed JSON",
  "message": "Invalid JSON format.",
  "path": "/api/movies",
  "validationErrors": []
}
```

### 4. Type Mismatch (400)

```json
{
  "timestamp": "2026-02-06T11:00:00",
  "status": 400,
  "error": "Type Mismatch",
  "message": "Invalid value 'abc' for parameter 'id'. Expected type: Long",
  "path": "/api/movies/abc",
  "validationErrors": []
}
```

## ✔️ Validation Rules

### Movie Object Validation

| Field | Rules |
|-------|-------|
| **title** | Required, cannot be empty or whitespace |
| **rating** | Optional, must be between 0 and 10 |
| **releaseYear** | Optional, must be between 1888 and 2100 |
| **description** | Optional |
| **genre** | Optional |

## 🧪 Testing with cURL

### Get all movies
```bash
curl -X GET http://localhost:8080/api/movies
```

### Get movie by ID
```bash
curl -X GET http://localhost:8080/api/movies/1
```

### Add new movie
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Interstellar",
    "description": "A team of explorers travel through a wormhole in space",
    "genre": "Sci-Fi",
    "releaseYear": 2014,
    "rating": 8.6
  }'
```

### Update movie
```bash
curl -X PUT http://localhost:8080/api/movies/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Shawshank Redemption",
    "description": "Updated description",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.3
  }'
```

### Delete movie
```bash
curl -X DELETE http://localhost:8080/api/movies/1
```

## 🧪 Testing with Postman

1. Import the API endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. Use the examples above for request bodies
4. Check response status codes and messages

## 🎯 Sample Data

The application comes pre-populated with these movies:

1. **The Shawshank Redemption** (1994) - Drama - 9.3/10
2. **Inception** (2010) - Sci-Fi - 8.8/10
3. **The Dark Knight** (2008) - Action - 9.0/10


## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Shuddhodan Ingale**
- GitHub: [@Shuddhodan Ingale](https://github.com/Shudhu7)
- Email: mr.shudhuingle@gmail.com
