package com.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {
    private Long actorId;
    private String name;
    private String gender;
    private List<Long> movieIds;
}
