package com.godel.olizarovich.services;

import com.godel.olizarovich.config.SpringJdbcConfig;
import com.godel.olizarovich.models.Director;
import com.godel.olizarovich.models.Film;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJdbcConfig.class)
public class FilmServiceTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    public FilmService filmService;
    @Autowired
    public DirectorService directorService;

    @Before
    public void clearTable() {
        JdbcTestUtils.deleteFromTables(filmService.getFilmAccess().getJdbcTemplate(), "Film");
        JdbcTestUtils.deleteFromTables(directorService.getDirectorAccess().getJdbcTemplate(), "Director");
    }

    @Test
    public void saveAndRead() {
        Director director = new Director();
        director.setFirstName("Test1");
        director.setLastName("Testlastname1");
        director.setBirthDate(LocalDate.now());
        directorService.save(director);

        Film film = new Film();
        film.setDirector(director);
        film.setName("TestName1");
        film.setGenre("TestGenre1");
        film.setReleaseDate(LocalDate.now());

        int id = (int)filmService.save(film);

        Film filmFromDataBase = filmService.getById(id);

        Assert.assertEquals(film, filmFromDataBase);
    }

    @Test
    public void getNotExisting() {
        Assert.assertNull(filmService.getById(5));
    }

    @Test
    public void delete() {
        Director director = new Director();
        director.setFirstName("Test1");
        director.setLastName("Testlastname1");
        director.setBirthDate(LocalDate.now());
        directorService.save(director);

        Film film = new Film();
        film.setDirector(director);
        film.setName("TestName1");
        film.setGenre("TestGenre1");
        film.setReleaseDate(LocalDate.now());
        filmService.save(film);

        Assert.assertTrue(filmService.delete(film));
    }

    @Test
    public void update() {
        Director director = new Director();
        director.setFirstName("Test1");
        director.setLastName("Testlastname1");
        director.setBirthDate(LocalDate.now());
        directorService.save(director);

        Film film = new Film();
        film.setDirector(director);
        film.setName("TestName1");
        film.setGenre("TestGenre1");
        film.setReleaseDate(LocalDate.now());
        filmService.save(film);

        film.setName("TestName2");

        Assert.assertTrue(filmService.update(film));
    }

    @Test
    public void getByDirectorName() {
        JdbcTestUtils.dropTables(filmService.getFilmAccess().getJdbcTemplate(), "Film");
        JdbcTestUtils.dropTables(directorService.getDirectorAccess().getJdbcTemplate(), "Director");

        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("database/defineSchemaPostgresql.sql"));
        tables.addScript(new ClassPathResource("/database/fillDatabasePostgre.sql"));
        DatabasePopulatorUtils.execute(tables, dataSource);

        int count = filmService.getByDatesAndDirector(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-02-02"), "Kiarra", "Deziree").size();
        Assert.assertEquals(count, 1);
    }
}