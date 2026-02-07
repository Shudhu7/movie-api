# 🎬 Movie API - Spring Boot REST API

A RESTful API for managing a movie collection built with Spring Boot. This API provides complete CRUD (Create, Read, Update, Delete) operations with robust error handling, input validation, and consistent response formatting.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Request & Response Examples](#request--response-examples)
- [Error Handling](#error-handling)
- [Validation Rules](#validation-rules)
- [CORS Configuration](#cors-configuration)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- ✅ Full CRUD operations for movies
- ✅ In-memory data storage using ArrayList
- ✅ Comprehensive error handling with custom exceptions
- ✅ Success messages for all mutation operations (POST, PUT, DELETE)
- ✅ Input validation with detailed error responses
- ✅ Auto-incrementing IDs using AtomicLong
- ✅ Pre-populated sample data on startup
- ✅ RESTful API design principles
- ✅ Consistent response format for errors and successes
- ✅ CORS enabled for cross-origin requests
- ✅ Global exception handling

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
├── config/
│   └── WebConfig.java                # CORS configuration
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

   You should see the message:
   ```
   Movie API Application started successfully!
   ```

4. **Access the API**
   - Local: `http://localhost:8080`
   - Production: `https://movie-api-production-c15e.up.railway.app/api/movies`

## 🔌 API Endpoints

### Base URL

**Local Development:**
```
http://localhost:8080
```

**Production (Railway):**
```
https://movie-api-production-c15e.up.railway.app/api/movies
```

### Available Endpoints

| Method | Endpoint | Description | Response Type |
|--------|----------|-------------|---------------|
| GET    | `/api/movies` | Get all movies | Array of Movie objects |
| GET    | `/api/movies/{id}` | Get movie by ID | Single Movie object |
| POST   | `/api/movies` | Add a new movie | SuccessResponse with Movie |
| PUT    | `/api/movies/{id}` | Update a movie | SuccessResponse with Movie |
| DELETE | `/api/movies/{id}` | Delete a movie | SuccessResponse (no data) |

## 📝 Request & Response Examples

### 1. Get All Movies

**Request:**
```http
GET /api/movies
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "title": "The Shawshank Redemption",
    "description": "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.3
  },
  {
    "id": 2,
    "title": "Inception",
    "description": "A thief who steals corporate secrets through the use of dream-sharing technology.",
    "genre": "Sci-Fi",
    "releaseYear": 2010,
    "rating": 8.8
  },
  {
    "id": 3,
    "title": "The Dark Knight",
    "description": "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham.",
    "genre": "Action",
    "releaseYear": 2008,
    "rating": 9.0
  }
]
```

### 2. Get Movie by ID

**Request:**
```http
GET /api/movies/1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "The Shawshank Redemption",
  "description": "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
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

**Response (201 CREATED):**
```json
{
  "timestamp": "2026-02-07T10:30:00",
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

**Response (200 OK):**
```json
{
  "timestamp": "2026-02-07T10:35:00",
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

**Response (200 OK):**
```json
{
  "timestamp": "2026-02-07T10:40:00",
  "status": 200,
  "message": "Movie deleted successfully!",
  "details": "The movie 'The Matrix' has been removed from the collection.",
  "data": null
}
```

## ❌ Error Handling

The API uses a `GlobalExceptionHandler` to provide consistent, detailed error responses for various scenarios.

### 1. Movie Not Found (404)

**Request:**
```http
GET /api/movies/999
```

**Response:**
```json
{
  "timestamp": "2026-02-07T10:45:00",
  "status": 404,
  "error": "Not Found",
  "message": "Movie not found with id: 999",
  "path": "/api/movies/999",
  "validationErrors": []
}
```

### 2. Validation Error (400)

**Request:**
```http
POST /api/movies
Content-Type: application/json

{
  "title": "Test Movie",
  "rating": 15
}
```

**Response:**
```json
{
  "timestamp": "2026-02-07T10:50:00",
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

### 3. Empty Title Validation (400)

**Request:**
```http
POST /api/movies
Content-Type: application/json

{
  "title": "",
  "genre": "Drama"
}
```

**Response:**
```json
{
  "timestamp": "2026-02-07T10:52:00",
  "status": 400,
  "error": "Validation Error",
  "message": "Title is required and cannot be empty",
  "path": "/api/movies",
  "validationErrors": [
    {
      "field": "title",
      "message": "Title is required and cannot be empty"
    }
  ]
}
```

### 4. Invalid JSON Format (400)

**Request:**
```http
POST /api/movies
Content-Type: application/json

{
  "title": "Test Movie",
  "rating": "invalid",
}
```

**Response:**
```json
{
  "timestamp": "2026-02-07T10:55:00",
  "status": 400,
  "error": "Malformed JSON",
  "message": "Invalid JSON format.",
  "path": "/api/movies",
  "validationErrors": []
}
```

### 5. Type Mismatch (400)

**Request:**
```http
GET /api/movies/abc
```

**Response:**
```json
{
  "timestamp": "2026-02-07T11:00:00",
  "status": 400,
  "error": "Type Mismatch",
  "message": "Invalid value 'abc' for parameter 'id'. Expected type: Long",
  "path": "/api/movies/abc",
  "validationErrors": []
}
```

### 6. Server Error (500)

Any unexpected exceptions are caught and returned with a generic error message:

```json
{
  "timestamp": "2026-02-07T11:05:00",
  "status": 500,
  "error": "Server Error",
  "message": "Something went wrong.",
  "path": "/api/movies",
  "validationErrors": []
}
```

## ✔️ Validation Rules

### Movie Object Validation

| Field | Type | Rules | Required |
|-------|------|-------|----------|
| **id** | Long | Auto-generated, not required in requests | No |
| **title** | String | Cannot be null, empty, or whitespace only | Yes |
| **description** | String | No validation | No |
| **genre** | String | No validation | No |
| **releaseYear** | Integer | Must be between 1888 and 2100 if provided | No |
| **rating** | Double | Must be between 0.0 and 10.0 if provided | No |

### Validation Details

- **Title Validation**: Enforced in `validateMovie()` method in `MovieService`
- **Rating Validation**: Rating out of 10 (e.g., 8.5 means 8.5/10)
- **Release Year Validation**: 1888 is the year of the first movie ever made
- **ID Generation**: Uses `AtomicLong` for thread-safe auto-incrementing IDs

## 🌐 CORS Configuration

CORS is configured in `WebConfig.java` to allow cross-origin requests:

```java
.allowedOrigins("*")  // Allows all origins
.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
.allowedHeaders("*")
.allowCredentials(false)
.maxAge(3600)
```

This enables the API to be accessed from:
- Frontend applications on different domains
- File protocol (file://)
- Deployment platforms (Netlify, Vercel, etc.)

## 🧪 Testing

### Testing with cURL

#### Get all movies
```bash
curl -X GET https://movie-api-production-c15e.up.railway.app/api/movies
```

#### Get movie by ID
```bash
curl -X GET https://movie-api-production-c15e.up.railway.app/api/movies1
```

#### Add new movie
```bash
curl -X POST https://movie-api-production-c15e.up.railway.app/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Interstellar",
    "description": "A team of explorers travel through a wormhole in space",
    "genre": "Sci-Fi",
    "releaseYear": 2014,
    "rating": 8.6
  }'
```

#### Update movie
```bash
curl -X PUT https://movie-api-production-c15e.up.railway.app/api/movies/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Shawshank Redemption",
    "description": "Updated description",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.3
  }'
```

#### Delete movie
```bash
curl -X DELETE https://movie-api-production-c15e.up.railway.app/api/movies/1
```

### Testing with Postman

1. **Import the API endpoints** into Postman
2. **Set the base URL** to `https://movie-api-production-c15e.up.railway.app`
3. **Test each endpoint** using the examples provided above
4. **Verify response status codes**:
   - 200 OK for successful GET, PUT, DELETE
   - 201 CREATED for successful POST
   - 400 BAD REQUEST for validation errors
   - 404 NOT FOUND for missing resources
   - 500 INTERNAL SERVER ERROR for unexpected errors

### Testing with Browser

Simply navigate to:
```
https://movie-api-production-c15e.up.railway.app/api/movies
```

You'll see a JSON response with all movies.

## 🎯 Pre-populated Sample Data

The application starts with these three movies:

| ID | Title | Genre | Year | Rating |
|----|-------|-------|------|--------|
| 1 | The Shawshank Redemption | Drama | 1994 | 9.3/10 |
| 2 | Inception | Sci-Fi | 2010 | 8.8/10 |
| 3 | The Dark Knight | Action | 2008 | 9.0/10 |

## 🔄 Data Persistence

**Note**: This API uses an **in-memory ArrayList** for data storage. This means:
- Data is lost when the application restarts
- All movies revert to the initial sample data on restart
- Perfect for development, testing, and demonstration purposes
- For production use, consider integrating with a database (MySQL, PostgreSQL, MongoDB, etc.)

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Contribution Guidelines

- Follow existing code style and conventions
- Add comments for complex logic
- Update documentation for new features
- Test your changes thoroughly

## 👨‍💻 Author

**Shuddhodan Ingale**
- GitHub: [@Shuddhodan Ingale](https://github.com/Shudhu7)
- Email: mr.shudhuingle@gmail.com

