package com.movie.mapper;

import com.movie.dto.ActorDto;
import com.movie.entity.Actor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T09:56:26+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.41.0.z20250115-2156, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ActorMapperImpl implements ActorMapper {

    @Override
    public ActorDto toDto(Actor actor) {
        if ( actor == null ) {
            return null;
        }

        ActorDto actorDto = new ActorDto();

        actorDto.setActorId( actor.getActorId() );
        actorDto.setName( actor.getName() );
        actorDto.setGender( actor.getGender() );
        actorDto.setMovieIds( mapMoviesToIds( actor.getMovies() ) );

        return actorDto;
    }

    @Override
    public Actor toEntity(ActorDto actorDto) {
        if ( actorDto == null ) {
            return null;
        }

        Actor actor = new Actor();

        actor.setActorId( actorDto.getActorId() );
        actor.setName( actorDto.getName() );
        actor.setGender( actorDto.getGender() );

        return actor;
    }
}
