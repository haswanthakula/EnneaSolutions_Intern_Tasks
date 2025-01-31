package com.movie.service;

import com.movie.entity.BoxOffice;
import com.movie.repository.BoxOfficeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Double getAverageBudget() {
        return boxOfficeRepository.findAll().stream()
            .mapToDouble(BoxOffice::getBudget)
            .average()
            .orElse(0.0);
    }

    @Transactional(readOnly = true)
    public Page<BoxOffice> getAllBoxOfficeRecordsPaginated(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return boxOfficeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<BoxOffice> getBoxOfficeRecordsByBudgetPaginated(Double budgetThreshold, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return boxOfficeRepository.findByBudgetGreaterThan(budgetThreshold, pageable);
    }
}
