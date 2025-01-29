package com.movie.repository;

import com.movie.entity.BoxOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxOfficeRepository extends JpaRepository<BoxOffice, Long> {
}
