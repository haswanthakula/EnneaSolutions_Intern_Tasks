package com.movie.service;

import com.movie.entity.Movie;
import com.movie.entity.BoxOffice;
import com.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

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

    public Page<Movie> getMoviesAfterYear(int year, Pageable pageable) {
        return movieRepository.findByReleaseYearGreaterThan(year, pageable);
    }

    public Page<Movie> getMoviesSortedByYearDesc(Pageable pageable) {
        return movieRepository.findAllByOrderByReleaseYearDesc(pageable);
    }

    public List<Map<String, Object>> getMoviesWithDirectors() {
        return movieRepository.findAll().stream()
            .map(movie -> {
                Map<String, Object> movieMap = new HashMap<>();
                movieMap.put("movie_title", movie.getTitle());
                movieMap.put("director_name", movie.getDirector().getName());
                return movieMap;
            })
            .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getMoviesWithBoxOffice() {
        return movieRepository.findAll().stream()
            .map(movie -> {
                Map<String, Object> movieMap = new HashMap<>();
                movieMap.put("movie_title", movie.getTitle());
                BoxOffice boxOffice = movie.getBoxOffice();
                movieMap.put("budget", boxOffice != null ? boxOffice.getBudget() : null);
                movieMap.put("box_office_collection", boxOffice != null ? boxOffice.getBoxOfficeCollection() : null);
                return movieMap;
            })
            .collect(Collectors.toList());
    }

    public Long getMovieCountByYear(int year) {
        return movieRepository.countByReleaseYear(year);
    }

    @Transactional(readOnly = true)
    public Page<Movie> getAllMoviesPaginated(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return movieRepository.findAll(pageable);
    }

    public Page<Movie> getMoviesAfterYearPaginated(int year, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return movieRepository.findByReleaseYearGreaterThan(year, pageable);
    }

    public Page<Movie> getMoviesSortedByYearDescPaginated(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return movieRepository.findAllByOrderByReleaseYearDesc(pageable);
    }
}
