package com.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long movieId;
    private String title;
    private Integer releaseYear;
    private Long directorId;
    private List<Long> actorIds;
    private BoxOfficeDto boxOffice;
}
