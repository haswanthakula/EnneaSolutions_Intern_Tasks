package com.movie.controller;

import com.movie.dto.MovieCastDTO;
import com.movie.service.MovieCastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie-cast")
@RequiredArgsConstructor
public class MovieCastController {
    private final MovieCastService movieCastService;


    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<MovieCastDTO>> getMovieCastByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieCastService.getMovieCastByMovieId(movieId));
    }

    @GetMapping("/actor/{actorId}")
    public ResponseEntity<List<MovieCastDTO>> getMovieCastByActorId(@PathVariable Long actorId) {
        return ResponseEntity.ok(movieCastService.getMovieCastByActorId(actorId));
    }

    @DeleteMapping("/{movieId}/{actorId}/{roleName}")
    public ResponseEntity<Void> deleteMovieCast(
            @PathVariable Long movieId,
            @PathVariable Long actorId,
            @PathVariable String roleName) {
        movieCastService.deleteMovieCast(movieId, actorId, roleName);
        return ResponseEntity.noContent().build();
    }
}
