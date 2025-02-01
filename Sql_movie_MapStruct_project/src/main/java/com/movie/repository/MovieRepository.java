package com.movie.repository;

import com.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByReleaseYearGreaterThan(int year, Pageable pageable);
    Page<Movie> findAllByOrderByReleaseYearDesc(Pageable pageable);
    Long countByReleaseYear(int year);
}
