package com.movie.mapper;

import com.movie.dto.BoxOfficeDto;
import com.movie.entity.BoxOffice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoxOfficeMapper {
    @Mapping(target = "movieId", source = "movieId")
    @Mapping(target = "budget", source = "budget")
    @Mapping(target = "boxOfficeCollection", source = "boxOfficeCollection")
    BoxOfficeDto toDto(BoxOffice boxOffice);

    @Mapping(target = "movieId", source = "movieId")
    @Mapping(target = "budget", source = "budget")
    @Mapping(target = "boxOfficeCollection", source = "boxOfficeCollection")
    BoxOffice toEntity(BoxOfficeDto boxOfficeDto);
}
