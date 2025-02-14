package com.movie.service;

import com.movie.dto.MovieCastDTO;
import com.movie.entity.Actor;
import com.movie.entity.Movie;
import com.movie.entity.MovieCast;
import com.movie.entity.MovieCastId;
import com.movie.mapper.MovieCastMapper;
import com.movie.repository.ActorRepository;
import com.movie.repository.MovieCastRepository;
import com.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MovieCastService {
    private final MovieCastRepository movieCastRepository;
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final MovieCastMapper movieCastMapper;

    @Transactional
    public MovieCastDTO createMovieCast(MovieCastDTO movieCastDTO) {
        Movie movie = movieRepository.findById(movieCastDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Actor actor = actorRepository.findById(movieCastDTO.getActorId())
                .orElseThrow(() -> new RuntimeException("Actor not found"));

        MovieCast movieCast = new MovieCast();
        movieCast.setMovie(movie);
        movieCast.setActor(actor);
        movieCast.setRoleName(movieCastDTO.getRoleName());

        MovieCast savedMovieCast = movieCastRepository.save(movieCast);
        return movieCastMapper.toDTO(savedMovieCast);
    }

    @Transactional(readOnly = true)
    public List<MovieCastDTO> getMovieCastByMovieId(Long movieId) {
        return movieCastRepository.findByMovie_MovieId(movieId)
                .stream()
                .map(movieCastMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieCastDTO> getMovieCastByActorId(Long actorId) {
        return movieCastRepository.findByActor_ActorId(actorId)
                .stream()
                .map(movieCastMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieCastDTO> getMovieCastByRoleName(String roleName) {
        return movieCastRepository.findByRoleNameContaining(roleName)
                .stream()
                .map(movieCastMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieCastDTO> getAllMovieCasts() {
        return movieCastRepository.findAll()
                .stream()
                .map(movieCastMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMovieCast(Long movieId, Long actorId, String roleName) {
        MovieCast movieCast = movieCastRepository.findById(new MovieCastId(movieId, actorId, roleName))
                .orElseThrow(() -> new RuntimeException("Movie Cast not found"));
        movieCastRepository.delete(movieCast);
    }
}
