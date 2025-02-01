package com.movie.repository;

import com.movie.entity.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Page<Director> findByNameContaining(String name, Pageable pageable);
    Page<Director> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
