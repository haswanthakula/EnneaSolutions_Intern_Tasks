package com.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxOfficeDto {
    private Long movieId;
    private Long budget;
    private Long boxOfficeCollection;

    // Conversion methods to handle potential double inputs
    public void setBudget(double budget) {
        this.budget = (long) budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public void setBoxOfficeCollection(double boxOfficeCollection) {
        this.boxOfficeCollection = (long) boxOfficeCollection;
    }

    public void setBoxOfficeCollection(Long boxOfficeCollection) {
        this.boxOfficeCollection = boxOfficeCollection;
    }
}
