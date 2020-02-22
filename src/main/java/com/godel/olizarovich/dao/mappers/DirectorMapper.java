package com.godel.olizarovich.dao.mappers;

import com.godel.olizarovich.models.Director;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorMapper implements RowMapper<Director> {
    @Override
    public Director mapRow(ResultSet rs, int i) throws SQLException {
        Director director = new Director();

        director.setId(rs.getInt("id"));
        director.setFirstName(rs.getString("first_name"));
        director.setLastName(rs.getString("last_name"));
        director.setBirthDate(rs.getDate("birth_date").toLocalDate());

        return director;
    }
}
