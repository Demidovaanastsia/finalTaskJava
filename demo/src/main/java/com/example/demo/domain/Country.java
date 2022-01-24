package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "COUNTRY")
public class Country {

    @Id
    @SequenceGenerator(
            name = "country_sequence",
            sequenceName = "country_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "country_sequence")
    @Column(name = "id",
            updatable = false)
    @JsonView(View.filmsView.class)
    private Long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "TEXT")
    @JsonView(View.filmsView.class)
    private String name;

    @OneToMany(mappedBy="country", orphanRemoval = true)
    private Set<Film> films = new HashSet<Film>();

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return name;
    }
}
