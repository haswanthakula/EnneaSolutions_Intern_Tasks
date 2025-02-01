package com.movie.controller;

import com.movie.dto.DirectorDto;
import com.movie.entity.Director;
import com.movie.entity.Movie;
import com.movie.mapper.DirectorMapper;
import com.movie.repository.MovieRepository;
import com.movie.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;
    private final DirectorMapper directorMapper;
    private static final Logger logger = LoggerFactory.getLogger(DirectorController.class);

    @GetMapping
    public ResponseEntity<List<DirectorDto>> getAllDirectors() {
        List<Director> directors = directorService.getAllDirectors();
        List<DirectorDto> directorDtos = directors.stream()
                .map(directorMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(directorDtos);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DirectorDto>> getAllDirectorsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Director> directors = directorService.getAllDirectorsPaginated(page, size);
        Page<DirectorDto> directorDtos = directors.map(directorMapper::toDto);
        return ResponseEntity.ok(directorDtos);
    }

    @Autowired
    private MovieRepository movieRepository;

    @DeleteMapping("/{directorId}/movie/{movieId}")
    public ResponseEntity<?> deleteMovieFromDirector(
            @PathVariable Long directorId,
            @PathVariable Long movieId) {
        try {
            logger.info("Attempting to remove movie {} from director {}", movieId, directorId);
            
            // Fetch the movie before deletion to log its details
            Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
            
            logger.info("Movie details before deletion: Title={}, Current Director ID={}", 
                movie.getTitle(), 
                movie.getDirector() != null ? movie.getDirector().getDirectorId() : "No Director");
            
            // Check if movie belongs to the director
            if (movie.getDirector() == null || !movie.getDirector().getDirectorId().equals(directorId)) {
                return ResponseEntity.badRequest().body(
                    Map.of("error", "Movie does not belong to the specified director",
                            "movieId", movieId,
                            "directorId", directorId)
                );
            }
            
            // Perform the deletion
            directorService.deleteMovieFromDirector(directorId, movieId);
            
            // Return detailed success response
            return ResponseEntity.ok(Map.of(
                "message", "Successfully removed movie from director",
                "movieId", movieId,
                "directorId", directorId,
                "movieTitle", movie.getTitle()
            ));
        } catch (Exception e) {
            logger.error("Error in deleteMovieFromDirector", e);
            return ResponseEntity.badRequest().body(
                Map.of("error", "Failed to delete movie",
                        "message", e.getMessage(),
                        "movieId", movieId,
                        "directorId", directorId)
            );
        }
    }
}
