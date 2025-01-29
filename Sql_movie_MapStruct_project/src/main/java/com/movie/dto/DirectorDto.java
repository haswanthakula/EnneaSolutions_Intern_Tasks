package com.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {
    private Long directorId;
    private String name;
    private String awards;
    private List<Long> movieIds;
}
