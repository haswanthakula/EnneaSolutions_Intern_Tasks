package com.movie.service;

import com.movie.entity.Actor;
import com.movie.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

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

    public List<Map<String, Object>> getActorNameAndGender() {
        return actorRepository.findAll().stream()
            .map(actor -> {
                Map<String, Object> actorMap = new HashMap<>();
                actorMap.put("name", actor.getName());
                actorMap.put("gender", actor.getGender());
                return actorMap;
            })
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Actor> getAllActorsPaginated(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return actorRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Actor> getActorsByGenderPaginated(String gender, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return actorRepository.findByGender(gender, pageable);
    }
}
