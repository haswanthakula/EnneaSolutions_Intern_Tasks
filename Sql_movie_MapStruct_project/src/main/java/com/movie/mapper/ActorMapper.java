package com.movie.mapper;

import com.movie.dto.ActorDto;
import com.movie.entity.Actor;
import com.movie.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    @Mapping(target = "actorId", source = "actorId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "movieIds", source = "movies", qualifiedByName = "mapMoviesToIds")
    ActorDto toDto(Actor actor);

    @Mapping(target = "actorId", source = "actorId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "gender", source = "gender")
    Actor toEntity(ActorDto actorDto);

    @Named("mapMoviesToIds")
    default List<Long> mapMoviesToIds(List<Movie> movies) {
        return movies == null ? Collections.emptyList() : movies.stream()
                .map(movie -> movie != null ? movie.getMovieId() : null)
                .filter(id -> id != null)
                .collect(Collectors.toList());
    }
}
