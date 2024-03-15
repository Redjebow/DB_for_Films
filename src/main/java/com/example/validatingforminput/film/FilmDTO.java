package com.example.validatingforminput.film;

import com.example.validatingforminput.actor.Actor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class FilmDTO    {
    @NotNull
    @Size(max = 20)
    private String title;
    @NotNull
    @Size(max = 20)
    private String reTitle;
    @NotNull
    private String genre;
    @NotNull
    @Min(value =1900, message = "po golqmo ot 1900")
    private int year;
    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @NotNull
    @Size(min = 1, max = 4)
    private Set<Actor> actors;

    boolean isLeapYear;

    public boolean isLeapYear() {
        return isLeapYear;
    }

    public void setLeapYear(boolean leapYear) {
        isLeapYear = leapYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public String getReTitle() {
        return reTitle;
    }

    public void setReTitle(String reTitle) {
        this.reTitle = reTitle;
    }
}
