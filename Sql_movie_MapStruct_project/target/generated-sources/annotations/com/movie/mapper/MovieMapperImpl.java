package com.movie.mapper;

import com.movie.dto.BoxOfficeDto;
import com.movie.dto.MovieDto;
import com.movie.entity.BoxOffice;
import com.movie.entity.Director;
import com.movie.entity.Movie;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-29T13:01:43+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.41.0.z20250115-2156, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDto toDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setMovieId( movie.getMovieId() );
        movieDto.setDirectorId( movieDirectorDirectorId( movie ) );
        movieDto.setActorIds( mapActorsToIds( movie.getActors() ) );
        movieDto.setBoxOffice( boxOfficeToBoxOfficeDto( movie.getBoxOffice() ) );
        movieDto.setReleaseYear( movie.getReleaseYear() );
        movieDto.setTitle( movie.getTitle() );

        return movieDto;
    }

    @Override
    public Movie toEntity(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setMovieId( movieDto.getMovieId() );
        movie.setDirector( mapDirector( movieDto.getDirectorId() ) );
        movie.setActors( mapActors( movieDto.getActorIds() ) );
        movie.setBoxOffice( boxOfficeDtoToBoxOffice( movieDto.getBoxOffice() ) );
        movie.setReleaseYear( movieDto.getReleaseYear() );
        movie.setTitle( movieDto.getTitle() );

        return movie;
    }

    private Long movieDirectorDirectorId(Movie movie) {
        if ( movie == null ) {
            return null;
        }
        Director director = movie.getDirector();
        if ( director == null ) {
            return null;
        }
        Long directorId = director.getDirectorId();
        if ( directorId == null ) {
            return null;
        }
        return directorId;
    }

    protected BoxOfficeDto boxOfficeToBoxOfficeDto(BoxOffice boxOffice) {
        if ( boxOffice == null ) {
            return null;
        }

        BoxOfficeDto boxOfficeDto = new BoxOfficeDto();

        boxOfficeDto.setMovieId( boxOffice.getMovieId() );
        boxOfficeDto.setBudget( boxOffice.getBudget() );
        boxOfficeDto.setBoxOfficeCollection( boxOffice.getBoxOfficeCollection() );

        return boxOfficeDto;
    }

    protected BoxOffice boxOfficeDtoToBoxOffice(BoxOfficeDto boxOfficeDto) {
        if ( boxOfficeDto == null ) {
            return null;
        }

        BoxOffice boxOffice = new BoxOffice();

        boxOffice.setBoxOfficeCollection( boxOfficeDto.getBoxOfficeCollection() );
        boxOffice.setBudget( boxOfficeDto.getBudget() );
        boxOffice.setMovieId( boxOfficeDto.getMovieId() );

        return boxOffice;
    }
}
