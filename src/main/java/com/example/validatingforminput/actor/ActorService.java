package com.example.validatingforminput.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    @Autowired
    ActorRepository actorRepository;
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
