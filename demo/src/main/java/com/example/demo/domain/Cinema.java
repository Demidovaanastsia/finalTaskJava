package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "CINEMA")
public class Cinema {

    @Id
    @SequenceGenerator(
            name = "cinema_sequence",
            sequenceName = "cinema_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "cinema_sequence")
    @Column(name = "id",
            updatable = false)
    @JsonView({View.filmsView.class, View.cinemasView.class})
    private Long id;

    @Column(name = "title",
            nullable = false,
            columnDefinition = "TEXT")
    @JsonView({View.filmsView.class, View.cinemasView.class})
    private String title;

    @Column(name = "address",
            columnDefinition = "TEXT")
    @JsonView({View.filmsView.class, View.cinemasView.class})
    private String address;

    @ManyToMany(targetEntity = Film.class,
            mappedBy = "cinemas")
    @JsonView(View.cinemasView.class)
    private Set<Film> films = new HashSet<Film>();

    public Cinema() {
    }

    public Cinema(String title, String address) {
        this.title = title;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
