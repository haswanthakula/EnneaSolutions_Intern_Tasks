package com.movie.controller;

import com.movie.dto.DirectorDto;
import com.movie.entity.Director;
import com.movie.mapper.DirectorMapper;
import com.movie.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;
    private final DirectorMapper directorMapper;

    @GetMapping
    public ResponseEntity<List<DirectorDto>> getAllDirectors() {
        List<Director> directors = directorService.getAllDirectors();
        List<DirectorDto> directorDtos = directors.stream()
                .map(directorMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(directorDtos);
    }
}
