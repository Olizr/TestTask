package com.godel.olizarovich.services;

import com.godel.olizarovich.dao.access.DirectorAccess;
import com.godel.olizarovich.models.Director;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class DirectorService {
    private DirectorAccess directorAccess;

    @Autowired
    public DirectorService(DirectorAccess directorAccess) {
        this.directorAccess = directorAccess;
    }

    public List<Director> getAll() {
        return directorAccess.getAll();
    }

    public List<Director> getWithQuery(String query, Object[] params) {
        return directorAccess.getWithQuery(query, params);
    }

    public List<Director> getWithNames(String firstName, String lastName) {
        List<Director> directors = directorAccess.getAll();
        if (!firstName.isEmpty())
            directors = directors.stream().filter(x -> x.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());

        if (!lastName.isEmpty())
            directors = directors.stream().filter(x -> x.getLastName().equalsIgnoreCase(lastName))
                    .collect(Collectors.toList());
        return directors;
    }

    public Director getById(long id) {
        return directorAccess.get(id);
    }

    public long add(Director director) {
        return directorAccess.save(director);
    }

    public boolean update(Director director){
        return directorAccess.update(director);
    }

    public boolean delete(Director director) {
        return directorAccess.delete(director);
    }
}
