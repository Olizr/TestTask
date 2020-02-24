package com.godel.olizarovich.services;

import com.godel.olizarovich.dao.access.FilmAccess;
import com.godel.olizarovich.models.Director;
import com.godel.olizarovich.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private FilmAccess filmAccess;
    private DirectorService directorService;

    @Autowired
    public FilmService(FilmAccess filmAccess, DirectorService directorService) {
        this.filmAccess = filmAccess;
        this.directorService = directorService;
    }

    public List<Film> getAll() {
        return findDirectors(filmAccess.getAll());
    }

    public List<Film> getWithQuery(String query, Object[] params) {
        return findDirectors(filmAccess.getWithQuery(query, params));
    }

    public Film getById(long id) {
        return findDirector(filmAccess.get(id));
    }

    public long save(Film film) {
        return filmAccess.save(film);
    }

    public boolean update(Film film){
        return filmAccess.update(film);
    }

    public boolean delete(Film film) {
        return filmAccess.delete(film);
    }

    public List<Film> getByDirectorName(String firstName, String lastName) {
        List<Director> directors = directorService.getWithNames(firstName, lastName);

        List<Film> films = filmAccess.getAll().stream().filter(film ->
                directors.stream().anyMatch(dir ->
                {
                    if(dir.getId().equals(film.getDirectorId())) {
                        film.setDirector(dir);
                        return true;
                    }
                return false;
                }))
                .collect(Collectors.toList());

        return findDirectors(films);
    }

    public List<Film> getByDates(LocalDate start, LocalDate end) {
        List<Film> films = filmAccess.getAll().stream().filter(x ->
                x.getReleaseDate().compareTo(start) > -1 && x.getReleaseDate().compareTo(end) < 1)
                .collect(Collectors.toList());
        return findDirectors(films);
    }

    public List<Film> getByDatesAndDirector(LocalDate start, LocalDate end, String firstName, String lastName) {
        List<Film> films = getByDirectorName(firstName, lastName);

        return films.stream().filter(x ->
                x.getReleaseDate().compareTo(start) > -1 && x.getReleaseDate().compareTo(end) < 1)
                .collect(Collectors.toList());
    }

    private List<Film> findDirectors(List<Film> films) {
        for(Film film : films) {
            film.setDirector(directorService.getById(film.getDirectorId()));
        }

        return films;
    }

    private Film findDirector(Film film) {
        if (film != null)
            film.setDirector(directorService.getById(film.getDirectorId()));
        return film;
    }

    public FilmAccess getFilmAccess() {
        return filmAccess;
    }
}
