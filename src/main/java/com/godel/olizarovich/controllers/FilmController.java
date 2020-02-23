package com.godel.olizarovich.controllers;

import com.godel.olizarovich.models.Film;
import com.godel.olizarovich.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/")
    public List<Film> getAll() {

        return filmService.getAll();
    }

    @GetMapping("/byDirector")
    public List<Film> getById(@RequestParam(defaultValue = "") String firstName,
                              @RequestParam(defaultValue = "") String lastName) {

        return filmService.getByDirectorName(firstName, lastName);
    }

    @GetMapping("/byDates")
    public List<Film> getByDate(@RequestParam(defaultValue = "1900-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                              @RequestParam(defaultValue = "2900-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return filmService.getByDates(start, end);
    }
}
