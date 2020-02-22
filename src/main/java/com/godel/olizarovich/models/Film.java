package com.godel.olizarovich.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

public class Film {
    private Integer id;
    private Integer directorId;
    private Director director;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String genre;

    public Film() {
    }

    public Film(Integer id, Integer directorId, String name, LocalDate releaseDate, String genre) {
        this.id = id;
        this.directorId = directorId;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id) &&
                Objects.equals(directorId, film.directorId) &&
                Objects.equals(name, film.name) &&
                Objects.equals(genre, film.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, directorId, name, genre);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }
}
