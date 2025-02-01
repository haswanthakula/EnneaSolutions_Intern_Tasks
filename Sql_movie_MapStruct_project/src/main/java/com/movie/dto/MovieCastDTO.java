package com.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieCastDTO {
    private Long movieId;
    private Long actorId;
    private String roleName;
    private String movieTitle;
    private String actorName;
}
