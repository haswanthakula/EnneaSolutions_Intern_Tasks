package com.movie.service;

import com.movie.entity.Director;
import com.movie.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectorService {
    private final DirectorRepository directorRepository;

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
}
