package com.movie.controller;

import com.movie.dto.DirectorDto;
import com.movie.entity.Director;
import com.movie.mapper.DirectorMapper;
import com.movie.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/movies-count")
    public ResponseEntity<List<Map<String, Object>>> getDirectorsMovieCount() {
        List<Map<String, Object>> directorsMovieCount = directorService.getDirectorsMovieCount();
        return ResponseEntity.ok(directorsMovieCount);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DirectorDto>> getAllDirectorsPaginated(
            @RequestParam(defaultValue = "0") int page) {
        Page<Director> directors = directorService.getAllDirectorsPaginated(page);
        Page<DirectorDto> directorDtos = directors.map(directorMapper::toDto);
        return ResponseEntity.ok(directorDtos);
    }

    @GetMapping("/by-name/paginated")
    public ResponseEntity<Page<DirectorDto>> getDirectorsByNamePaginated(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page) {
        Page<Director> directors = directorService.getDirectorsByNamePaginated(name, page);
        Page<DirectorDto> directorDtos = directors.map(directorMapper::toDto);
        return ResponseEntity.ok(directorDtos);
    }
}
