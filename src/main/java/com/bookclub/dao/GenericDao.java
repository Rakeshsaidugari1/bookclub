package com.bookclub.dao;

import java.util.List;

public interface GenericDao<T, ID> {
    T save(T entity);  // Save an entity
    T find(ID id);  // Find entity by ID
    List<T> list();  // List all entities
    void delete(ID id);  // Delete entity by ID
}