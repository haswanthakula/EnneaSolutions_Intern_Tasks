package com.movie.repository;

import com.movie.entity.BoxOffice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxOfficeRepository extends JpaRepository<BoxOffice, Long> {
    Page<BoxOffice> findByBudgetGreaterThan(Double budget, Pageable pageable);
}
