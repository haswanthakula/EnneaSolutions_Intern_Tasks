package com.movie.controller;

import com.movie.dto.ActorDto;
import com.movie.entity.Actor;
import com.movie.mapper.ActorMapper;
import com.movie.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;
    private final ActorMapper actorMapper;

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAllActors() {
        List<Actor> actors = actorService.getAllActors();
        List<ActorDto> actorDtos = actors.stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(actorDtos);
    }

    @GetMapping("/select-columns")
    public ResponseEntity<List<Map<String, Object>>> getActorNameAndGender() {
        List<Map<String, Object>> actorColumns = actorService.getActorNameAndGender();
        return ResponseEntity.ok(actorColumns);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ActorDto>> getAllActorsPaginated(
            @RequestParam(defaultValue = "0") int page) {
        Page<Actor> actors = actorService.getAllActorsPaginated(page);
        Page<ActorDto> actorDtos = actors.map(actorMapper::toDto);
        return ResponseEntity.ok(actorDtos);
    }
}
