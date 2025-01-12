# SQL Project

## Created a Movies DataBase With Directors, Actors, Movies, Movie_Cast and Box_Office

```
CREATE DATABASE mymoviesdb;
SHOW DATABASES;
USE DATABASE;
```

### 1.) Creating Directors table:
```
CREATE TABLE Directors (director_id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255) NOT NULL,awards TEXT);
```

### Insert data into Directors:
```
INSERT INTO Directors (name, awards)
VALUES 
('S. S. Rajamouli', 'Padma Shri, National Award'),
('Christopher Nolan', 'Oscar Nomination'),
('Steven Spielberg', 'Oscar, BAFTA'),
('Quentin Tarantino', 'Oscar, Golden Globe'),
('James Cameron', 'Oscar, Golden Globe, BAFTA'),
('Martin Scorsese', 'Oscar, Golden Globe, BAFTA');
```

### 2.) Create Actors table:
```
CREATE TABLE Actors (actor_id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255) NOT NULL,gender CHAR(1));
```

### Insert data into Actors:
```
INSERT INTO Actors (name, gender)
VALUES 
('Prabhas', 'M'),
('Leonardo DiCaprio', 'M'),
('Sam Neill', 'M'),
('Uma Thurman', 'F'),
('Kate Winslet', 'F'),
('Tom Hanks', 'M'),
('Morgan Freeman', 'M'),
('Scarlett Johansson', 'F'),
('Matt Damon', 'M'),
('Natalie Portman', 'F'),
('Christian Bale', 'M'),
('Anne Hathaway', 'F'),
('Robert Downey Jr.', 'M'),
('Chris Evans', 'M'),
('Emma Stone', 'F');
```

### 3.) Create Movies table:
```
CREATE TABLE Movies (movie_id INT PRIMARY KEY AUTO_INCREMENT,title VARCHAR(255) NOT NULL,release_year YEAR NOT NULL,director_id INT,FOREIGN KEY (director_id) REFERENCES Directors(director_id));
```

### Insert sample data into Movies:
```
INSERT INTO Movies (title, release_year, director_id)
VALUES 
('Baahubali', 2015, 1),
('Inception', 2010, 2),
('Jurassic Park', 1993, 3),
('Pulp Fiction', 1994, 4),
('Titanic', 1997, 5),
('Avatar', 2009, 5),
('The Wolf of Wall Street', 2013, 6);
```

### 4.) Create Movie_Cast table:
```
CREATE TABLE Movie_Cast (movie_id INT,actor_id INT,role_name VARCHAR(255),additional_roles TEXT,PRIMARY KEY (movie_id, actor_id, role_name),FOREIGN KEY (movie_id) REFERENCES Movies(movie_id),FOREIGN KEY (actor_id) REFERENCES Actors(actor_id));
```

### Insert data into Movie_Cast:
```
INSERT INTO Movie_Cast (movie_id, actor_id, role_name, additional_roles)
VALUES 
(1, 1, 'Baahubali', 'King of Mahishmati'),
(2, 2, 'Dom Cobb', 'Dream Extractor'),
(3, 3, 'Dr. Alan Grant', 'Paleontologist'),
(1, 3, 'Supporting Role', 'General who supports Baahubali'),
(5, 5, 'Jack Dawson', 'Romantic Lead'),
(6, 6, 'Jake Sully', 'Marine turned Avatar'),
(4, 4, 'Mia Wallace', 'Mob Boss Associate'),
(7, 7, 'Jordan Belfort', 'Stockbroker turned Conman'),
(6, 9, 'Supporting Role', 'Scientist on Pandora'),
(2, 11, 'Supporting Role', 'Dream Security Team Member');
```

### 5.) Create Box_Office table:
```
CREATE TABLE Box_Office (movie_id INT,budget BIGINT,box_office_collection BIGINT,FOREIGN KEY (movie_id) REFERENCES Movies(movie_id),PRIMARY KEY (movie_id));
```

### Insert data into Box_Office:
```
INSERT INTO Box_Office (movie_id, budget, box_office_collection)
VALUES 
(1, 1800000000, 6500000000),
(2, 160000000, 830000000),
(3, 63000000, 1030000000),
(4, 8000000, 214000000),
(5, 200000000, 2187000000),
(6, 237000000, 2847000000),
(7, 100000000, 392000000);
```


## Relationships

### a.) Directors → Movies (1-to-Many):
A director can direct multiple movies.

### b.) Movies → Movie_Cast (Many-to-Many):
A movie can have multiple actors, and an actor can act in multiple movies.

### c.) Movies → Box_Office (1-to-1):
Each movie has one box office record.


##  Basic SQL Queries

```
SHOW tables;
DESCRIBE table_name;
```

### Select all records from a table:
```
SELECT * FROM Actors;
```

### Select specific columns:
```
SELECT name, gender FROM Actors;
```

### Select with a WHERE condition:
```
SELECT * FROM Movies WHERE release_year > 2000;
```

### ORDER BY (sorting results):
```
SELECT * FROM Movies ORDER BY release_year DESC;
```

### AVG (calculate the average value):
```
SELECT AVG(budget) FROM Box_Office;
```

### COUNT (count rows based on condition):
```
SELECT COUNT(*) FROM Movies WHERE release_year = 2010;
```

### GROUP BY (group results):
```
SELECT director_id, COUNT(*) AS movie_count FROM Movies GROUP BY director_id;
```

### UPDATE Actors
```
SET gender = 'M' WHERE actor_id = 1;
```

### DELETE a record:
```
DELETE FROM Movies WHERE movie_id = 3;
```

## SQL queries Using JOINs

### INNER JOIN 
retrieves actors and the movies they have acted in based on matching actor_id
```
SELECT a.name AS actor_name, m.title AS movie_title
FROM Actors a
INNER JOIN Movie_Cast mc ON a.actor_id = mc.actor_id
INNER JOIN Movies m ON mc.movie_id = m.movie_id;
```

### LEFT JOIN
retrieves all actors and the movies they have acted in, including actors who haven't acted in any movie
```
SELECT a.name AS actor_name, m.title AS movie_title
FROM Actors a
LEFT JOIN Movie_Cast mc ON a.actor_id = mc.actor_id
LEFT JOIN Movies m ON mc.movie_id = m.movie_id;
```

## Other Queries

retrieves the movie titles and their box office collections
```
SELECT m.title AS movie_title, b.box_office_collection
FROM Movies m
INNER JOIN Box_Office b ON m.movie_id = b.movie_id;
```

retrieves actor names, movie titles, and their box office collections
```
SELECT a.name AS actor_name, m.title AS movie_title, b.box_office_collection
FROM Actors a
INNER JOIN Movie_Cast mc ON a.actor_id = mc.actor_id
INNER JOIN Movies m ON mc.movie_id = m.movie_id
INNER JOIN Box_Office b ON m.movie_id = b.movie_id;
```
uery identifies actors who have acted in more than one movie
```
SELECT a.name AS actor_name, COUNT(mc.movie_id) AS number_of_movies
FROM Actors a
INNER JOIN Movie_Cast mc ON a.actor_id = mc.actor_id
GROUP BY a.name
HAVING COUNT(mc.movie_id) > 1;
```

query shows the total number of actors for each movie
```
SELECT m.title AS movie_title, COUNT(a.actor_id) AS number_of_actors
FROM Movies m
INNER JOIN Movie_Cast mc ON m.movie_id = mc.movie_id
INNER JOIN Actors a ON mc.actor_id = a.actor_id
GROUP BY m.title;
```






