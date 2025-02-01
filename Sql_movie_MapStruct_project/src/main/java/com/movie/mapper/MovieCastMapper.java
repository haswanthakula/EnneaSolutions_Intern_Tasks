package com.movie.mapper;

import com.movie.dto.MovieCastDTO;
import com.movie.entity.MovieCast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieCastMapper {
    MovieCastMapper INSTANCE = Mappers.getMapper(MovieCastMapper.class);

    @Mapping(source = "movie.movieId", target = "movieId")
    @Mapping(source = "actor.actorId", target = "actorId")
    @Mapping(source = "movie.title", target = "movieTitle")
    @Mapping(source = "actor.name", target = "actorName")
    MovieCastDTO toDTO(MovieCast movieCast);

    @Mapping(target = "movie.movieId", source = "movieId")
    @Mapping(target = "actor.actorId", source = "actorId")
    MovieCast toEntity(MovieCastDTO movieCastDTO);
}
