package com.example.demo.controller;

import com.example.demo.domain.Film;
import com.example.demo.domain.View;
import com.example.demo.exception.FilmNotFoundException;
import com.example.demo.repository.FilmRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
class FilmController {

    private final FilmRepository filmRepository;

    @Autowired
    FilmController(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    @GetMapping("/films")
    @JsonView(View.filmsView.class)
    public List<Film> getAllFilms() {

        List<Film> films = filmRepository.findAll();

        return films;
    }

    @PostMapping("/films")
    @JsonView(View.filmsView.class)
    public Film createNewFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }


    @GetMapping("/films/{id}")
    @JsonView(View.filmsView.class)
    public Film getFilmById(@PathVariable Long id) {

        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }

    @PutMapping("/films/{id}")
    @JsonView(View.filmsView.class)
    public Film replaceFilm(@RequestBody Film film, @PathVariable Long id) {

        return filmRepository.findById(id)
                .map(currFilm -> {
                    currFilm.setTitle(film.getTitle());
                    currFilm.setDescription(film.getDescription());
                    currFilm.setMark(film.getMark());
                    currFilm.setActive(film.getActive());
                    currFilm.setCountry(film.getCountry());
                    currFilm.setActors(film.getActors());
                    currFilm.setCinemas(film.getCinemas());
                    currFilm.setGenres(film.getGenres());
                    return filmRepository.save(currFilm);
                })
                .orElseGet(() -> {
                    film.setId(id);
                    return filmRepository.save(film);
                });
    }

    @DeleteMapping("/films/{id}")
    void deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
    }

}
