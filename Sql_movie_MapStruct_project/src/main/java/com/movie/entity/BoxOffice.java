package com.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Box_Office")
public class BoxOffice {
    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "budget")
    private Long budget;

    @Column(name = "box_office_collection")
    private Long boxOfficeCollection;

    @OneToOne
    @MapsId
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private Movie movie;
}
