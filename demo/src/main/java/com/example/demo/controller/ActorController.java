package com.example.demo.controller;


import com.example.demo.domain.Actor;
import com.example.demo.domain.View;
import com.example.demo.exception.ActorNotFoundException;
import com.example.demo.repository.ActorRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
class ActorController {

    private final ActorRepository actorRepository;

    @Autowired
    ActorController(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    @GetMapping("/actors")
    @JsonView(View.actorsView.class)
    public List<Actor> getAllActors() {

        List<Actor> actors = actorRepository.findAll();

        return actors;
    }

    @PostMapping("/actors")
    @JsonView(View.actorsView.class)
    public Actor createNewActor(@RequestBody Actor actor) {
        return actorRepository.save(actor);
    }


    @GetMapping("/actors/{id}")
    @JsonView(View.actorsView.class)
    public Actor getActorById(@PathVariable Long id) {

        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    @PutMapping("/actors/{id}")
    @JsonView(View.actorsView.class)
    public Actor replaceActor(@RequestBody Actor actor, @PathVariable Long id) {

        return actorRepository.findById(id)
                .map(currActor -> {
                    currActor.setFirstName(actor.getFirstName());
                    currActor.setLastName(actor.getLastName());
                    currActor.setPatronymicName(actor.getPatronymicName());
                    currActor.setAge(actor.getAge());
                    currActor.setFilms(actor.getFilms());
                    return actorRepository.save(currActor);
                })
                .orElseGet(() -> {
                    actor.setId(id);
                    return actorRepository.save(actor);
                });
    }

    @DeleteMapping("/actors/{id}")
    void deleteActor(@PathVariable Long id) {
        actorRepository.deleteById(id);
    }

}

