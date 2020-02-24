package com.godel.olizarovich.services;

import com.godel.olizarovich.config.SpringJdbcConfig;
import com.godel.olizarovich.models.Director;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;


import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJdbcConfig.class)
public class DirectorServiceTest {
    @Autowired
    public DirectorService directorService;

    @Before
    public void clearTable() {
        JdbcTestUtils.deleteFromTables(directorService.getDirectorAccess().getJdbcTemplate(), "Director");
    }

    @Test
    public void saveAndRead() {
        Director director = new Director();
        director.setFirstName("Test1");
        director.setLastName("Testlastname1");
        director.setBirthDate(LocalDate.now());
        int id = (int)directorService.save(director);

        Director directorFromDatabase = directorService.getById(id);

        Assert.assertEquals(director, directorFromDatabase);
    }

    @org.junit.Test
    public void getNotExisting() {
        Assert.assertNull(directorService.getById(5));
    }

    @Test
    public void update() {
        Director director = new Director();
        director.setFirstName("Test1");
        director.setLastName("Testlastname1");
        director.setBirthDate(LocalDate.now());
        directorService.save(director);

        director.setLastName("Test2");

        Assert.assertTrue(directorService.update(director));
    }

    @Test
    public void delete() {
        Director director = new Director();
        director.setFirstName("Test1");
        director.setLastName("Testlastname1");
        director.setBirthDate(LocalDate.now());
        directorService.save(director);

        Assert.assertTrue(directorService.delete(director));
    }
}