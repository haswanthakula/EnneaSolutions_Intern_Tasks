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
@Table(name = "movie_cast")
@IdClass(MovieCastId.class)
public class MovieCast {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id")
    @ToString.Exclude
    private Actor actor;

    @Id
    @Column(name = "role_name", length = 255)
    private String roleName;
}
