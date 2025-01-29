package com.movie.service;

import com.movie.entity.Actor;
import com.movie.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorService {
    private final ActorRepository actorRepository;

    @Transactional(readOnly = true)
    public List<Actor> getAllActors() {
        try {
            log.info("Fetching all actors with eager loading");
            List<Actor> actors = actorRepository.findAll();
            // Eagerly initialize collections and associations
            actors.forEach(actor -> {
                Hibernate.initialize(actor.getMovies());
            });
            log.info("Retrieved {} actors", actors.size());
            return actors;
        } catch (Exception e) {
            log.error("Error retrieving actors", e);
            throw new RuntimeException("Failed to retrieve actors", e);
        }
    }
}
