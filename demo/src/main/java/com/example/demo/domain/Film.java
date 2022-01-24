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
@Table(name = "FILM")
public class Film {

    @Id
    @SequenceGenerator(
            name = "film_sequence",
            sequenceName = "film_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "film_sequence")
    @Column(name = "id",
            updatable = false)
    @JsonView({View.filmsView.class, View.cinemasView.class, View.actorsView.class})
    private Long id;

    @Column(name = "title",
            nullable = false,
            columnDefinition = "TEXT")
    @JsonView({View.filmsView.class, View.cinemasView.class, View.actorsView.class})
    private String title;

    @Column(name = "description",
            columnDefinition = "TEXT")
    @JsonView(View.filmsView.class)
    private String description;

    @Column(name = "mark")
    @JsonView(View.filmsView.class)
    private int mark;

    @Column(name = "is_active",
            nullable = false)
    @JsonView({View.filmsView.class, View.cinemasView.class, View.actorsView.class})
    private boolean isActive;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name="country_id")
    @JsonView(View.filmsView.class)
    private Country country;

    @ManyToMany(targetEntity = Actor.class)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName="id")
    )
    @JsonView(View.filmsView.class)
    private Set<Actor> actors = new HashSet<Actor>();


    @ManyToMany(targetEntity = Genre.class)
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName="id")
    )
    @JsonView({View.filmsView.class, View.cinemasView.class, View.actorsView.class})
    private Set<Genre> genres = new HashSet<Genre>();

    @ManyToMany(targetEntity = Cinema.class)
    @JoinTable(name = "film_cinema",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id", referencedColumnName="id")
    )
    @JsonView(View.filmsView.class)
    private Set<Cinema> cinemas = new HashSet<Cinema>();

    public Film() {
    }

    public Film(String title, String description, Integer mark, boolean isActive, Country country) {
        this.title = title;
        this.description = description;
        this.mark = mark;
        this.isActive = isActive;
        this.country = country;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(Set<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    @Override
    public String toString() {
        return title;
    }
}
