package com.movie.controller;

import com.movie.dto.BoxOfficeDto;
import com.movie.entity.BoxOffice;
import com.movie.mapper.BoxOfficeMapper;
import com.movie.service.BoxOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/box-office")
@RequiredArgsConstructor
public class BoxOfficeController {
    private final BoxOfficeService boxOfficeService;
    private final BoxOfficeMapper boxOfficeMapper;

    @GetMapping
    public ResponseEntity<List<BoxOfficeDto>> getAllBoxOfficeRecords() {
        List<BoxOffice> boxOfficeRecords = boxOfficeService.getAllBoxOfficeRecords();
        List<BoxOfficeDto> boxOfficeDtos = boxOfficeRecords.stream()
                .map(boxOfficeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(boxOfficeDtos);
    }

    @GetMapping("/average-budget")
    public ResponseEntity<Double> getAverageBudget() {
        Double averageBudget = boxOfficeService.getAverageBudget();
        return ResponseEntity.ok(averageBudget);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<BoxOfficeDto>> getAllBoxOfficeRecordsPaginated(
            @RequestParam(defaultValue = "0") int page) {
        Page<BoxOffice> boxOfficeRecords = boxOfficeService.getAllBoxOfficeRecordsPaginated(page);
        Page<BoxOfficeDto> boxOfficeDtos = boxOfficeRecords.map(boxOfficeMapper::toDto);
        return ResponseEntity.ok(boxOfficeDtos);
    }
}
