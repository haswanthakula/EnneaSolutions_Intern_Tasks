package com.movie.service;

import com.movie.entity.Director;
import com.movie.repository.DirectorRepository;
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

    public List<Map<String, Object>> getDirectorsMovieCount() {
        return directorRepository.findAll().stream()
            .map(director -> {
                Map<String, Object> directorMap = new HashMap<>();
                directorMap.put("director_name", director.getName());
                directorMap.put("number_of_movies", director.getMovies().size());
                return directorMap;
            })
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Director> getAllDirectorsPaginated(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return directorRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Director> getDirectorsByNamePaginated(String name, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return directorRepository.findByNameContaining(name, pageable);
    }
}
