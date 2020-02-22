package com.godel.olizarovich.dao.mappers;

import com.godel.olizarovich.models.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet rs, int i) throws SQLException {
        Film film = new Film();

        film.setId(rs.getInt("id"));
        film.setDirectorId(rs.getInt("director_id"));
        film.setName(rs.getString("name"));
        film.setReleaseDate(rs.getDate("release_date").toLocalDate());
        film.setGenre(rs.getString("genre"));

        return film;
    }
}
