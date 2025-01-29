package com.movie.mapper;

import com.movie.dto.MovieDto;
import com.movie.entity.Actor;
import com.movie.entity.Director;
import com.movie.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "movieId", source = "movieId")
    @Mapping(target = "directorId", source = "director.directorId")
    @Mapping(target = "actorIds", source = "actors", qualifiedByName = "mapActorsToIds")
    @Mapping(target = "boxOffice", source = "boxOffice")
    MovieDto toDto(Movie movie);

    @Mapping(target = "movieId", source = "movieId")
    @Mapping(target = "director", source = "directorId", qualifiedByName = "mapDirector")
    @Mapping(target = "actors", source = "actorIds", qualifiedByName = "mapActors")
    @Mapping(target = "boxOffice", source = "boxOffice")
    Movie toEntity(MovieDto movieDto);

    @Named("mapActorsToIds")
    default List<Long> mapActorsToIds(List<Actor> actors) {
        return actors == null ? Collections.emptyList() : actors.stream()
                .map(actor -> actor != null ? actor.getActorId() : null)
                .filter(id -> id != null)
                .collect(Collectors.toList());
    }

    @Named("mapDirector")
    default Director mapDirector(Long directorId) {
        if (directorId == null) {
            return null;
        }
        Director director = new Director();
        director.setDirectorId(directorId);
        return director;
    }

    @Named("mapActors")
    default List<Actor> mapActors(List<Long> actorIds) {
        return actorIds == null ? Collections.emptyList() : actorIds.stream()
                .filter(id -> id != null)
                .map(id -> {
                    Actor actor = new Actor();
                    actor.setActorId(id);
                    return actor;
                })
                .collect(Collectors.toList());
    }
}
