package com.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieCastId implements Serializable {
    private Long movie;
    private Long actor;
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCastId that = (MovieCastId) o;
        return Objects.equals(movie, that.movie) &&
               Objects.equals(actor, that.actor) &&
               Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, actor, roleName);
    }
}
