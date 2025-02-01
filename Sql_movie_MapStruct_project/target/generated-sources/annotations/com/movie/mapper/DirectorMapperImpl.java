package com.movie.mapper;

import com.movie.dto.DirectorDto;
import com.movie.entity.Director;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-01T12:05:44+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.41.0.z20250115-2156, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class DirectorMapperImpl implements DirectorMapper {

    @Override
    public DirectorDto toDto(Director director) {
        if ( director == null ) {
            return null;
        }

        DirectorDto directorDto = new DirectorDto();

        directorDto.setDirectorId( director.getDirectorId() );
        directorDto.setName( director.getName() );
        directorDto.setAwards( director.getAwards() );
        directorDto.setMovieIds( mapMoviesToIds( director.getMovies() ) );

        return directorDto;
    }

    @Override
    public Director toEntity(DirectorDto directorDto) {
        if ( directorDto == null ) {
            return null;
        }

        Director director = new Director();

        director.setDirectorId( directorDto.getDirectorId() );
        director.setName( directorDto.getName() );
        director.setAwards( directorDto.getAwards() );

        return director;
    }
}
