package com.godel.olizarovich.dao.access;

import com.godel.olizarovich.config.QueryConstant;
import com.godel.olizarovich.models.Director;
import com.godel.olizarovich.dao.mappers.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class DirectorAccess implements Access<Director> {
    private Logger logger = Logger.getLogger(DirectorAccess.class);
    private final JdbcTemplate jdbcTemplate;

    private final String SQL_FIND;
    private final String SQL_GET_ALL;
    private final String SQL_INSERT;
    private final String SQL_UPDATE;
    private final String SQL_DELETE;

    @Autowired
    public DirectorAccess(DataSource dataSource, QueryConstant queryConstant) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        SQL_FIND = queryConstant.DIRECTOR_SQL_FIND;
        SQL_GET_ALL = queryConstant.DIRECTOR_SQL_GET_ALL;
        SQL_INSERT = queryConstant.DIRECTOR_SQL_INSERT;
        SQL_DELETE = queryConstant.DIRECTOR_SQL_DELETE;
        SQL_UPDATE = queryConstant.DIRECTOR_SQL_UPDATE;
    }

    @Override
    public Director get(long id) {
        Director director = null;
        try {
            director = jdbcTemplate.queryForObject(SQL_FIND, new Object[] {id}, new DirectorMapper());
        }
        catch (EmptyResultDataAccessException e) {
            logger.error("Object \"Film\" with id " + id + " not exist in database");
        }

        return director;
    }

    @Override
    public List<Director> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new DirectorMapper());
    }

    @Override
    public List<Director> getWithQuery(String query, Object[] params) {
        return jdbcTemplate.query(query, params, new DirectorMapper());
    }

    @Override
    public long save(Director director) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, director.getFirstName());
            ps.setString(2, director.getLastName());
            ps.setDate(3, Date.valueOf(director.getBirthDate()));
            return ps;
        }, keyHolder);

        director.setId((int)keyHolder.getKeys().get("id"));
        return director.getId();
    }

    @Override
    public boolean update(Director director) {
        return jdbcTemplate.update(SQL_UPDATE,
                director.getFirstName(),
                director.getLastName(),
                Date.valueOf(director.getBirthDate()),
                director.getId()) > 0;
    }

    @Override
    public boolean delete(Director director) {
        return jdbcTemplate.update(SQL_DELETE, director.getId()) > 0;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
