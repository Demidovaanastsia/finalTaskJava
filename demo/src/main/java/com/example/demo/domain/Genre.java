package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "genre_sequence")
    @Column(name = "id",
            updatable = false)
    @JsonView({View.filmsView.class, View.cinemasView.class, View.actorsView.class})
    private Long id;

    @Column(name = "title",
            nullable = false,
            columnDefinition = "TEXT")
    @JsonView({View.filmsView.class, View.cinemasView.class, View.actorsView.class})
    private String title;

    @ManyToMany(targetEntity = Film.class,
            mappedBy = "genres")
    private Set<Film> films = new HashSet<Film>();

    public Genre() {
    }

    public Genre(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return title;
    }
}
