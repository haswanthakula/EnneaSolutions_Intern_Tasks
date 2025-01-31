package com.movie.service;

import com.movie.entity.BoxOffice;
import com.movie.repository.BoxOfficeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoxOfficeService {
    private final BoxOfficeRepository boxOfficeRepository;

    @Value("${app.pagination.page-size:5}")
    private int pageSize;

    @Transactional(readOnly = true)
    public List<BoxOffice> getAllBoxOfficeRecords() {
        try {
            log.info("Fetching all box office records");
            List<BoxOffice> boxOfficeRecords = boxOfficeRepository.findAll();
            log.info("Retrieved {} box office records", boxOfficeRecords.size());
            return boxOfficeRecords;
        } catch (Exception e) {
            log.error("Error retrieving box office records", e);
            throw new RuntimeException("Failed to retrieve box office records", e);
        }
    }

    @Transactional(readOnly = true)
    public Double getAverageBudget() {
        try {
            log.info("Calculating average budget");
            Double avgBudget = boxOfficeRepository.findAverageBudget();
            log.info("Average budget calculated: {}", avgBudget);
            return avgBudget;
        } catch (Exception e) {
            log.error("Error calculating average budget", e);
            throw new RuntimeException("Failed to calculate average budget", e);
        }
    }

    @Transactional(readOnly = true)
    public Page<BoxOffice> getAllBoxOfficeRecordsPaginated(int page) {
        try {
            if (page < 0) {
                throw new IllegalArgumentException("Page index must not be less than zero!");
            }
            log.info("Fetching box office records for page: {}", page);
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<BoxOffice> records = boxOfficeRepository.findAll(pageable);
            log.info("Retrieved {} records for page {}", records.getNumberOfElements(), page);
            return records;
        } catch (Exception e) {
            log.error("Error retrieving paginated box office records for page: {}", page, e);
            throw new RuntimeException("Failed to retrieve paginated box office records", e);
        }
    }

    @Transactional(readOnly = true)
    public Page<BoxOffice> getBoxOfficeRecordsByBudgetPaginated(Double budgetThreshold, int page) {
        try {
            if (page < 0) {
                throw new IllegalArgumentException("Page index must not be less than zero!");
            }
            if (budgetThreshold == null) {
                throw new IllegalArgumentException("Budget threshold must not be null!");
            }
            log.info("Fetching box office records with budget > {} for page: {}", budgetThreshold, page);
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<BoxOffice> records = boxOfficeRepository.findByBudgetGreaterThan(budgetThreshold, pageable);
            log.info("Retrieved {} records for page {}", records.getNumberOfElements(), page);
            return records;
        } catch (Exception e) {
            log.error("Error retrieving filtered box office records for page: {} and budget threshold: {}", 
                page, budgetThreshold, e);
            throw new RuntimeException("Failed to retrieve filtered box office records", e);
        }
    }
}
