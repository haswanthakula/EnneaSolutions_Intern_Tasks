package com.movie.controller;

import com.movie.dto.MovieDto;
import com.movie.entity.Movie;
import com.movie.mapper.MovieMapper;
import com.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        List<MovieDto> movieDtos = movies.stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieDtos);
    }
}
