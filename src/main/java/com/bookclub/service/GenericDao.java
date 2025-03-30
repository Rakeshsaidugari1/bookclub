package com.bookclub.service;

import java.util.List;

public interface GenericDao<E, K> {
    void save(E entity); // Method to save an entity
    E find(K key); // Method to find an entity by key
    void update(E entity); // Method to update an entity
    void delete(K key); // Method to delete an entity by key
    List<E> list(); // Method to list all entities
}
