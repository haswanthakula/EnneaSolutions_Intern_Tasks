package com.movie.mapper;

import com.movie.dto.BoxOfficeDto;
import com.movie.entity.BoxOffice;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T09:56:27+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.41.0.z20250115-2156, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BoxOfficeMapperImpl implements BoxOfficeMapper {

    @Override
    public BoxOfficeDto toDto(BoxOffice boxOffice) {
        if ( boxOffice == null ) {
            return null;
        }

        BoxOfficeDto boxOfficeDto = new BoxOfficeDto();

        boxOfficeDto.setMovieId( boxOffice.getMovieId() );
        boxOfficeDto.setBudget( boxOffice.getBudget() );
        boxOfficeDto.setBoxOfficeCollection( boxOffice.getBoxOfficeCollection() );

        return boxOfficeDto;
    }

    @Override
    public BoxOffice toEntity(BoxOfficeDto boxOfficeDto) {
        if ( boxOfficeDto == null ) {
            return null;
        }

        BoxOffice boxOffice = new BoxOffice();

        boxOffice.setMovieId( boxOfficeDto.getMovieId() );
        boxOffice.setBudget( boxOfficeDto.getBudget() );
        boxOffice.setBoxOfficeCollection( boxOfficeDto.getBoxOfficeCollection() );

        return boxOffice;
    }
}
