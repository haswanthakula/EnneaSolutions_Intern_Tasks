package com.movie.service;

import com.movie.entity.Movie;
import com.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        try {
            log.info("Fetching all movies with eager loading");
            List<Movie> movies = movieRepository.findAll();
            // Eagerly initialize collections and associations
            movies.forEach(movie -> {
                Hibernate.initialize(movie.getActors());
                Hibernate.initialize(movie.getBoxOffice());
                if (movie.getDirector() != null) {
                    Hibernate.initialize(movie.getDirector());
                }
            });
            log.info("Retrieved {} movies", movies.size());
            return movies;
        } catch (Exception e) {
            log.error("Error retrieving movies", e);
            throw new RuntimeException("Failed to retrieve movies", e);
        }
    }
}
