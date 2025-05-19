package com.bookclub.service;

import java.util.List;

public interface GenericDao<E, K> {
    void save(E entity);        // Save an entity
    E find(K key);              // Find an entity by key
    void update(E entity);      // Update an entity
    void delete(K key);         // Delete an entity by key
    List<E> list(K key);        // Updated: List entities based on a key
}
