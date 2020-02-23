package com.godel.olizarovich.dao.access;

import com.godel.olizarovich.config.QueryConstant;
import com.godel.olizarovich.models.Film;
import com.godel.olizarovich.dao.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class FilmAccess implements Access<Film> {
    private final JdbcTemplate jdbcTemplate;

    private final String SQL_FIND;
    private final String SQL_GET_ALL;
    private final String SQL_INSERT;
    private final String SQL_UPDATE;
    private final String SQL_DELETE;

    @Autowired
    public FilmAccess(DataSource dataSource, QueryConstant queryConstant) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        SQL_FIND = queryConstant.FILM_SQL_FIND;
        SQL_GET_ALL = queryConstant.FILM_SQL_GET_ALL;
        SQL_INSERT = queryConstant.FILM_SQL_INSERT;
        SQL_DELETE = queryConstant.FILM_SQL_DELETE;
        SQL_UPDATE = queryConstant.FILM_SQL_UPDATE;
    }

    @Override
    public Film get(long id) {
        return jdbcTemplate.queryForObject(SQL_FIND, new Object[] {id}, new FilmMapper());
    }

    @Override
    public List<Film> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new FilmMapper());
    }

    @Override
    public List<Film> getWithQuery(String query, Object[] params) {
        return jdbcTemplate.query(query, params, new FilmMapper());
    }

    @Override
    public long save(Film film) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, film.getDirectorId());
            ps.setString(2, film.getName());
            ps.setDate(3, Date.valueOf(film.getReleaseDate()));
            ps.setString(4, film.getGenre());
            return ps;
        }, keyHolder);

        film.setId((int)keyHolder.getKey().longValue());
        return keyHolder.getKey().longValue();
    }

    @Override
    public boolean update(Film film) {
        return jdbcTemplate.update(SQL_UPDATE,
                film.getDirectorId(),
                film.getName(),
                Date.valueOf(film.getReleaseDate()),
                film.getGenre(),
                film.getId()) > 0;
    }

    @Override
    public boolean delete(Film film) {
        return jdbcTemplate.update(SQL_DELETE, film.getId()) > 0;
    }
}
