package com.example.validatingforminput.actor;

import org.springframework.stereotype.Component;

@Component
public class ActorMapper {
    public Actor toEntity(ActorDTO actorDTO){
        Actor actor = new Actor();
        actor.setFirstName(actorDTO.getFirstName());
        actor.setLastName(actorDTO.getLastName());
        actor.setAge(actorDTO.getAge());
        actor.setNationality(actorDTO.getNationality());
        actor.setSex(actorDTO.getSex());
        actor.setTelephonNumber("0000000000");
        actor.setEmail("example@example.com");
        return actor;
    }
}
