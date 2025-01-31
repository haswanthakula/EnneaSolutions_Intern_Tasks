package com.movie.controller;

import com.movie.dto.MovieDto;
import com.movie.entity.Movie;
import com.movie.mapper.MovieMapper;
import com.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/after-year")
    public ResponseEntity<List<MovieDto>> getMoviesAfterYear(@RequestParam int year, Pageable pageable) {
        Page<Movie> movies = movieService.getMoviesAfterYear(year, pageable);
        List<MovieDto> movieDtos = movies.stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieDtos);
    }

    @GetMapping("/sorted-by-year")
    public ResponseEntity<List<MovieDto>> getMoviesSortedByYearDesc(Pageable pageable) {
        Page<Movie> movies = movieService.getMoviesSortedByYearDesc(pageable);
        List<MovieDto> movieDtos = movies.stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieDtos);
    }

    @GetMapping("/with-directors")
    public ResponseEntity<List<Map<String, Object>>> getMoviesWithDirectors() {
        List<Map<String, Object>> moviesWithDirectors = movieService.getMoviesWithDirectors();
        return ResponseEntity.ok(moviesWithDirectors);
    }

    @GetMapping("/with-box-office")
    public ResponseEntity<List<Map<String, Object>>> getMoviesWithBoxOffice() {
        List<Map<String, Object>> moviesWithBoxOffice = movieService.getMoviesWithBoxOffice();
        return ResponseEntity.ok(moviesWithBoxOffice);
    }

    @GetMapping("/count-by-year")
    public ResponseEntity<Long> getMovieCountByYear(@RequestParam int year) {
        Long count = movieService.getMovieCountByYear(year);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<MovieDto>> getAllMoviesPaginated(@RequestParam(defaultValue = "0") int page) {
        Page<Movie> movies = movieService.getAllMoviesPaginated(page);
        Page<MovieDto> movieDtos = movies.map(movieMapper::toDto);
        return ResponseEntity.ok(movieDtos);
    }

    @GetMapping("/after-year/paginated")
    public ResponseEntity<Page<MovieDto>> getMoviesAfterYearPaginated(
            @RequestParam int year, 
            @RequestParam(defaultValue = "0") int page) {
        Page<Movie> movies = movieService.getMoviesAfterYearPaginated(year, page);
        Page<MovieDto> movieDtos = movies.map(movieMapper::toDto);
        return ResponseEntity.ok(movieDtos);
    }

    @GetMapping("/sorted-by-year/paginated")
    public ResponseEntity<Page<MovieDto>> getMoviesSortedByYearDescPaginated(
            @RequestParam(defaultValue = "0") int page) {
        Page<Movie> movies = movieService.getMoviesSortedByYearDescPaginated(page);
        Page<MovieDto> movieDtos = movies.map(movieMapper::toDto);
        return ResponseEntity.ok(movieDtos);
    }
}
