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
@Table(name = "Actors")
public class Actor {
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", columnDefinition = "CHAR(1)")
    private String gender;

    @ManyToMany(mappedBy = "actors")
    @ToString.Exclude
    private List<Movie> movies;

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<MovieCast> movieCasts;
}
