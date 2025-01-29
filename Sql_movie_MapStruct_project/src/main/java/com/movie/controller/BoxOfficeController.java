package com.movie.controller;

import com.movie.dto.BoxOfficeDto;
import com.movie.entity.BoxOffice;
import com.movie.mapper.BoxOfficeMapper;
import com.movie.service.BoxOfficeService;
import lombok.RequiredArgsConstructor;
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
}
