package com.bookclub.service.dao;

import com.bookclub.model.Book;
import com.bookclub.service.GenericDao;
import java.util.List;

public interface BookDao extends GenericDao<Book, String> {
    // Add any additional methods specific to BookDao if needed
    List<Book> list(); // List method for books
    Book find(String isbn); // Find method for finding a book by ISBN
}
