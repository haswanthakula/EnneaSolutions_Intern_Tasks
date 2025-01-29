package com.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Directors")
public class Director {
    @Id
    @Column(name = "director_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "awards", columnDefinition = "TEXT")
    private String awards;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Movie> movies;
}
