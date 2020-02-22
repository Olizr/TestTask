package com.godel.olizarovich.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class QueryConstant {
    private Environment environment;

    public final String DIRECTOR_SQL_FIND;
    public final String DIRECTOR_SQL_GET_ALL;
    public final String DIRECTOR_SQL_INSERT;
    public final String DIRECTOR_SQL_UPDATE;
    public final String DIRECTOR_SQL_DELETE;

    public final String FILM_SQL_FIND;
    public final String FILM_SQL_GET_ALL;
    public final String FILM_SQL_INSERT;
    public final String FILM_SQL_UPDATE;
    public final String FILM_SQL_DELETE;

    @Autowired
    public QueryConstant(Environment environment) {
        this.environment  = environment;
        DIRECTOR_SQL_FIND = environment.getProperty("SQL.DIRECTOR.FIND");
        DIRECTOR_SQL_GET_ALL = environment.getProperty("SQL.DIRECTOR.GET_ALL");
        DIRECTOR_SQL_INSERT = environment.getProperty("SQL.DIRECTOR.INSERT");
        DIRECTOR_SQL_UPDATE = environment.getProperty("SQL.DIRECTOR.UPDATE");
        DIRECTOR_SQL_DELETE = environment.getProperty("SQL.DIRECTOR.DELETE");

        FILM_SQL_FIND = environment.getProperty("SQL.FILM.FIND");
        FILM_SQL_GET_ALL = environment.getProperty("SQL.FILM.GET_ALL");
        FILM_SQL_INSERT = environment.getProperty("SQL.FILM.INSERT");
        FILM_SQL_UPDATE = environment.getProperty("SQL.FILM.UPDATE");
        FILM_SQL_DELETE = environment.getProperty("SQL.FILM.DELETE");
    }
}
