package com.example.demo.controller;


import com.example.demo.domain.Cinema;
import com.example.demo.domain.View;
import com.example.demo.exception.CinemaNotFoundException;
import com.example.demo.repository.CinemaRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
class CinemaController {

    private final CinemaRepository cinemaRepository;

    @Autowired
    CinemaController(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @GetMapping("/cinemas")
    @JsonView(View.cinemasView.class)
    public List<Cinema> getAllCinemas() {

        List<Cinema> films = cinemaRepository.findAll();

        return films;
    }

    @PostMapping("/cinemas")
    @JsonView(View.cinemasView.class)
    public Cinema createNewCinema(@RequestBody Cinema cinema) {
        return cinemaRepository.save(cinema);
    }


    @GetMapping("/cinemas/{id}")
    @JsonView(View.cinemasView.class)
    public Cinema getCinemaById(@PathVariable Long id) {

        return cinemaRepository.findById(id)
                .orElseThrow(() -> new CinemaNotFoundException(id));
    }

    @PutMapping("/cinemas/{id}")
    @JsonView(View.cinemasView.class)
    public Cinema replaceCinema(@RequestBody Cinema cinema, @PathVariable Long id) {

        return cinemaRepository.findById(id)
                .map(currCinema -> {
                    currCinema.setTitle(cinema.getTitle());
                    currCinema.setAddress(cinema.getAddress());
                    currCinema.setFilms(cinema.getFilms());
                    return cinemaRepository.save(currCinema);
                })
                .orElseGet(() -> {
                    cinema.setId(id);
                    return cinemaRepository.save(cinema);
                });
    }

    @DeleteMapping("/cinemas/{id}")
    void deleteCinema(@PathVariable Long id) {
        cinemaRepository.deleteById(id);
    }

}
