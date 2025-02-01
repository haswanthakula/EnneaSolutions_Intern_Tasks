package com.movie.repository;

import com.movie.entity.MovieCast;
import com.movie.entity.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, MovieCastId> {
    List<MovieCast> findByMovie_MovieId(Long movieId);
    List<MovieCast> findByActor_ActorId(Long actorId);

    @Query("SELECT mc FROM MovieCast mc WHERE mc.roleName LIKE %:roleName%")
    List<MovieCast> findByRoleNameContaining(String roleName);
}
