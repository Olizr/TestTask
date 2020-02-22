package com.godel.olizarovich.dao.access;

import java.util.List;

public interface Access<T> {
    T get(long id);
    List<T> getWithQuery(String query, Object[] params);
    List<T> getAll();
    long save(T t);
    boolean update(T t);
    boolean delete(T t);
}
