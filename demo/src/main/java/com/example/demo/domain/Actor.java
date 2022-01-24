package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "ACTOR")
public class Actor {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "actor_sequence",
            sequenceName = "actor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "actor_sequence")
    @Column(name = "id",
            updatable = false)
    @JsonView({View.filmsView.class, View.actorsView.class})
    private Long id;

    @Column(name = "first_name",
            nullable = false,
            columnDefinition = "TEXT")
    @JsonView({View.filmsView.class, View.actorsView.class})
    private String firstName;

    @Column(name = "last_name",
            nullable = false,
            columnDefinition = "TEXT")
    @JsonView({View.filmsView.class, View.actorsView.class})
    private String lastName;

    @Column(name = "patronymic_name",
            columnDefinition = "TEXT")
    @JsonView({View.filmsView.class, View.actorsView.class})
    private String patronymicName;

    @Column(name = "age")
    @JsonView({View.filmsView.class, View.actorsView.class})
    private int age;

    @ManyToMany(targetEntity = Film.class,
            mappedBy = "actors")
    @JsonView(View.actorsView.class)
    private Set<Film> films = new HashSet<Film>();

    public Actor() {
    }

    public Actor(String firstName, String lastName, String patronymicName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
