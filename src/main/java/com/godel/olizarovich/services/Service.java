package com.godel.olizarovich.services;

import java.util.List;

public interface Service<T> {
    List<T> getAll();
    List<T> getWithQuery(String query, Object[] params);
    T getById();
    long add();
    boolean update(T t);
    boolean delete(T t);
}
