package com.movie.mapper;

import com.movie.dto.DirectorDto;
import com.movie.entity.Director;
import com.movie.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    @Mapping(target = "directorId", source = "directorId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "awards", source = "awards")
    @Mapping(target = "movieIds", source = "movies", qualifiedByName = "mapMoviesToIds")
    DirectorDto toDto(Director director);

    @Mapping(target = "directorId", source = "directorId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "awards", source = "awards")
    Director toEntity(DirectorDto directorDto);

    @Named("mapMoviesToIds")
    default List<Long> mapMoviesToIds(List<Movie> movies) {
        return movies == null ? Collections.emptyList() : movies.stream()
                .map(movie -> movie != null ? movie.getMovieId() : null)
                .filter(id -> id != null)
                .collect(Collectors.toList());
    }
}
