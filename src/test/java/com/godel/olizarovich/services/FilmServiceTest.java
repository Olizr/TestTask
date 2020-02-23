package com.godel.olizarovich.services;

import com.godel.olizarovich.config.SpringJdbcConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJdbcConfig.class)
class FilmServiceTest {

    @Autowired
    private FilmService filmService;

    @Test
    void getAll() {
        Assert.assertEquals(filmService.getAll().size(), 5);
    }

    @Test
    void getById() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}