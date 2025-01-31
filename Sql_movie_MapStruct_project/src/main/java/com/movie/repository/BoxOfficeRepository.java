package com.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movie.entity.BoxOffice;

@Repository
public interface BoxOfficeRepository extends JpaRepository<BoxOffice, Long> {
    Page<BoxOffice> findByBudgetGreaterThan(Double budget, Pageable pageable);

    @Query("SELECT AVG(b.budget) FROM BoxOffice b")
    Double findAverageBudget();
}
