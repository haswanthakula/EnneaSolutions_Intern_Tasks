Here’s the updated `README.md` file with the requested format, using `###` for headings and a clean structure:

---

# Movie Database API

## Overview
This API provides access to a comprehensive movie database, allowing users to interact with movie, director, actor, and box office data. Below are the available endpoints categorized by their respective controllers.

---

### Movie Controller APIs

#### Endpoints

- **Default Endpoint**  
  `GET http://localhost:8080/api/movies`  
  Retrieve all movies.

- **Movies After Specific Year**  
  `GET http://localhost:8080/api/movies/after-year?year=2010`  
  Get movies released after a specific year.

- **Movies Sorted by Year**  
  `GET http://localhost:8080/api/movies/sorted-by-year`  
  Get movies sorted by their release year.

- **Movies with Directors**  
  `GET http://localhost:8080/api/movies/with-directors`  
  Get movies along with their directors.

- **Movies with Box Office Information**  
  `GET http://localhost:8080/api/movies/with-box-office`  
  Get movies including box office information.

- **Paginated Movies**  
  `GET http://localhost:8080/api/movies/paginated`  
  `GET http://localhost:8080/api/movies/paginated?page=1&size=15`  
  Get a paginated list of movies.

- **Paginated Movies After Year**  
  `GET http://localhost:8080/api/movies/after-year/paginated?year=2010`  
  `GET http://localhost:8080/api/movies/after-year/paginated?year=2010&page=1`  
  Get paginated movies released after a specific year.

---

### Director Controller APIs

#### Endpoints

- **All Directors**  
  `GET http://localhost:8080/api/directors`  
  Retrieve all directors.

- **Paginated Directors**  
  `GET http://localhost:8080/api/directors/paginated`  
  `GET http://localhost:8080/api/directors/paginated?page=1&size=10`  
  Get a paginated list of directors.

- **Delete a Movie Associated with a Director**  
  `DELETE http://localhost:8080/api/directors/1/movie/1`  
  Delete a movie associated with a specific director.

---

### Box Office Controller APIs

#### Endpoints

- **All Box Office Records**  
  `GET http://localhost:8080/api/box-office`  
  Retrieve all box office records.

- **Average Budget**  
  `GET http://localhost:8080/api/box-office/average-budget`  
  Get the average budget of all movies.

- **Paginated Box Office Records**  
  `GET http://localhost:8080/api/box-office/paginated`  
  `GET http://localhost:8080/api/box-office/paginated?page=1&size=15`  
  Get a paginated list of box office records.

---

### Actor Controller APIs

#### Endpoints

- **All Actors**  
  `GET http://localhost:8080/api/actors`  
  Retrieve all actors.

- **Actors with Selected Columns**  
  `GET http://localhost:8080/api/actors/select-columns`  
  Get actors with selected columns.

- **Paginated Actors**  
  `GET http://localhost:8080/api/actors/paginated`  
  `GET http://localhost:8080/api/actors/paginated?page=1&size=10`  
  Get a paginated list of actors.

---

### Movie Cast Controller APIs

#### Endpoints

- **Get Movie Casts for a Specific Movie**  
  `GET http://localhost:8080/api/movie-cast/movie/1`  
  Retrieve movie casts for a specific movie.

- **Get Movie Casts for a Specific Actor**  
  `GET http://localhost:8080/api/movie-cast/actor/1`  
  Retrieve movie casts for a specific actor.

- **Delete a Specific Movie Cast Entry**  
  `DELETE http://localhost:8080/api/movie-cast/1/2/Lead`  
  Delete a specific movie cast entry.

---

### Database View Controller APIs

#### Endpoints

- **Home Page**  
  `GET http://localhost:8080/api/views/`  
  Home page view.

- **Movies View**  
  `GET http://localhost:8080/api/views/movies`  
  View all movies.

- **Actors View**  
  `GET http://localhost:8080/api/views/actors`  
  View all actors.

- **Directors View**  
  `GET http://localhost:8080/api/views/directors`  
  View all directors.

- **Box Office Records View**  
  `GET http://localhost:8080/api/views/box-office`  
  View all box office records.

---

### Project Setup and Running the Application

#### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven
- An IDE like IntelliJ IDEA or Eclipse (optional)

#### Steps to Run
1. Clone the repository.
2. Open the project in your preferred IDE.
3. Ensure all dependencies are downloaded.
4. Run the application.

#### Database Configuration
- The application uses an SQL database (details to be added).
- Database connection settings can be found in `application.properties`.

---

### Technologies Used
- Spring Boot
- MapStruct
- JPA/Hibernate
- SQL Database

---

### Contributing
Please read the contributing guidelines before making a pull request.

---

### License
[Add License Information Here]

---

This structure is clean, easy to read, and follows the `###` heading format you requested. Let me know if you need further adjustments! 🚀