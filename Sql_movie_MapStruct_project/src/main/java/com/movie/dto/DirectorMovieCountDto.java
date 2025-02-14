package com.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorMovieCountDto {
    private String directorName;
    private int numberOfMovies;
}
