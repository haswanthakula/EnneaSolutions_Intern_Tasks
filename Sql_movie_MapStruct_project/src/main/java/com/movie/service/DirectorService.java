package com.movie.service;

import com.movie.dto.DirectorMovieCountDto;
import com.movie.entity.Director;
import com.movie.entity.Movie;
import com.movie.repository.DirectorRepository;
import com.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;

    @Value("${app.pagination.page-size:5}")
    private int pageSize;

    public DirectorService(DirectorRepository directorRepository, MovieRepository movieRepository) {
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Transactional(readOnly = true)
    public List<Director> getAllDirectors() {
        try {
            log.info("Fetching all directors with eager loading");
            List<Director> directors = directorRepository.findAll();
            // Eagerly initialize collections and associations
            directors.forEach(director -> {
                Hibernate.initialize(director.getMovies());
            });
            log.info("Retrieved {} directors", directors.size());
            return directors;
        } catch (Exception e) {
            log.error("Error retrieving directors", e);
            throw new RuntimeException("Failed to retrieve directors", e);
        }
    }

    @Transactional(readOnly = true)
    public List<DirectorMovieCountDto> getDirectorsMovieCount() {
        try {
            log.info("Fetching all directors with movie count");
            return directorRepository.findAll().stream()
                .map(director -> new DirectorMovieCountDto(
                    director.getName(),
                    director.getMovies().size()
                ))
                .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error retrieving directors with movie count", e);
            throw new RuntimeException("Failed to retrieve directors with movie count", e);
        }
    }

    @Transactional(readOnly = true)
    public Page<Director> getAllDirectorsPaginated(int page, int size) {
        try {
            if (page < 0) {
                throw new IllegalArgumentException("Page index must not be less than zero!");
            }
            log.info("Fetching directors page {} with size {}", page, size);
            Pageable pageable = PageRequest.of(page, size);
            Page<Director> directors = directorRepository.findAll(pageable);
            log.info("Retrieved {} directors", directors.getNumberOfElements());
            return directors;
        } catch (Exception e) {
            log.error("Error retrieving paginated directors", e);
            throw new RuntimeException("Failed to retrieve paginated directors", e);
        }
    }

    @Transactional(readOnly = true)
    public Page<Director> getDirectorsByNamePaginated(String name, int page, int size) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        try {
            log.info("Searching directors by name '{}' page {} with size {}", name, page, size);
            Pageable pageable = PageRequest.of(page, size);
            Page<Director> directors = directorRepository.findByNameContainingIgnoreCase(name.trim(), pageable);
            log.info("Found {} directors matching '{}'", directors.getNumberOfElements(), name);
            return directors;
        } catch (Exception e) {
            log.error("Error searching directors by name '{}'", name, e);
            throw new RuntimeException("Failed to search directors by name", e);
        }
    }

    @Transactional
    public void deleteMovieFromDirector(Long directorId, Long movieId) {
        try {
            log.info("Attempting to remove movie {} from director {}", movieId, directorId);

            // Find the movie
            Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));
            
            // Find the director
            Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + directorId));

            // Log current state
            log.info("Current movie details: Title={}, Current Director ID={}", 
                movie.getTitle(), 
                movie.getDirector() != null ? movie.getDirector().getDirectorId() : "No Director");

            // Validate the movie belongs to the director
            if (movie.getDirector() == null || !movie.getDirector().getDirectorId().equals(directorId)) {
                log.error("Movie {} does not belong to director {}", movieId, directorId);
                throw new RuntimeException("Movie does not belong to the specified director");
            }

            // Remove the director from the movie
            movie.setDirector(null);
            movieRepository.save(movie);

            log.info("Successfully removed movie {} from director {}", movieId, directorId);
        } catch (Exception e) {
            log.error("Error deleting movie from director", e);
            throw new RuntimeException("Failed to delete movie from director: " + e.getMessage(), e);
        }
    }
}
