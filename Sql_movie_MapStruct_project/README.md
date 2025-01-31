# Movie Database API

## Overview
This API provides access to a comprehensive movie database, allowing users to interact with movie, director, actor, and box office data. Below are the available endpoints categorized by their respective controllers.

## Movie Controller APIs

### Endpoints

| Method | URL Pattern                         | Description                                     |
|--------|-------------------------------------|-------------------------------------------------|
| GET    | /                                  | Default endpoint to retrieve all movies         |
| GET    | /after-year                        | Get movies released after a specific year      |
| GET    | /sorted-by-year                   | Get movies sorted by their release year        |
| GET    | /with-directors                   | Get movies along with their directors           |
| GET    | /with-box-office                   | Get movies including box office information     |
| GET    | /count-by-year                     | Count the number of movies released by year     |
| GET    | /paginated                         | Get a paginated list of movies                  |
| GET    | /after-year/paginated             | Get paginated movies released after a specific year |
| GET    | /sorted-by-year/paginated         | Get paginated movies sorted by year             |

## Director Controller APIs

### Endpoints

| Method | URL Pattern                          | Description                                      |
|--------|--------------------------------------|--------------------------------------------------|
| GET    | /                                   | Get all directors                                |
| GET    | /movies-count                       | Get the count of movies for each director       |
| GET    | /paginated                          | Get a paginated list of directors                |
| GET    | /by-name/paginated                 | Get paginated directors filtered by name         |

## Box Office Controller APIs

### Endpoints

| Method | URL Pattern                          | Description                                      |
|--------|--------------------------------------|--------------------------------------------------|
| GET    | /                                   | Get all box office records                       |
| GET    | /average-budget                     | Get the average budget of movies                 |
| GET    | /paginated                          | Get a paginated list of box office records       |
| GET    | /by-budget/paginated                | Get paginated box office records filtered by budget |

## Actor Controller APIs

### Endpoints

| Method | URL Pattern                          | Description                                      |
|--------|--------------------------------------|--------------------------------------------------|
| GET    | /                                   | Get all actors                                   |
| GET    | /select-columns                     | Get actors with selected columns                 |
| GET    | /paginated                          | Get a paginated list of actors                   |
| GET    | /by-gender/paginated                | Get paginated actors filtered by gender          |

## Database View Controller APIs

### Endpoints

| Method | URL Pattern                          | Description                                      |
|--------|--------------------------------------|--------------------------------------------------|
| GET    | /                                   | Home page                                        |
| GET    | /movies                             | View all movies                                  |
| GET    | /actors                             | View all actors                                  |
| GET    | /directors                          | View all directors                               |
| GET    | /box-office                         | View all box office records                      |

## Pagination Example
To navigate through paginated results for directors, you can use the following URLs:

- **First Page**: `http://localhost:8080/api/directors/paginated`
- **Second Page**: `http://localhost:8080/api/directors/paginated?page=1`
- **Third Page**: `http://localhost:8080/api/directors/paginated?page=2`

## Project Setup and Running the Application

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven
- An IDE like IntelliJ IDEA or Eclipse (optional)

### Steps to Run
1. Clone the repository
2. Open the project in your preferred IDE
3. Ensure all dependencies are downloaded
4. Run the application

### Database Configuration
- The application uses SQL database (details to be added)
- Database connection settings can be found in `application.properties`

## Technologies Used
- Spring Boot
- MapStruct
- JPA/Hibernate
- SQL Database

## Contributing
Please read the contributing guidelines before making a pull request.

## License
[Add License Information Here]
