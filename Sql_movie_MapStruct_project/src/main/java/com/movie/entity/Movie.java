package com.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import com.movie.entity.MovieCast;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_year", columnDefinition = "YEAR")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    private Director director;

    @ManyToMany
    @JoinTable(
        name = "movie_cast",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @ToString.Exclude
    private List<Actor> actors;

    @OneToOne
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private BoxOffice boxOffice;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<MovieCast> movieCasts;
}
