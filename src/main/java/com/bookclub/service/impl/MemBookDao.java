package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import java.util.ArrayList;
import java.util.List;

public class MemBookDao implements BookDao {
    private List<Book> books;

    // Constructor to initialize the list of books
    public MemBookDao() {
        books = new ArrayList<>();
        // Adding sample books to the list
        books.add(new Book("978-3-16-148410-0", "Book One", "Description of Book One", 250, List.of("Author A")));
        books.add(new Book("978-1-23-456789-7", "Book Two", "Description of Book Two", 300, List.of("Author B")));
        books.add(new Book("978-0-12-345678-9", "Book Three", "Description of Book Three", 200, List.of("Author C")));
        books.add(new Book("978-3-16-148410-1", "Book Four", "Description of Book Four", 150, List.of("Author D")));
        books.add(new Book("978-0-14-243724-7", "Book Five", "Description of Book Five", 400, List.of("Author E")));
    }

    // Implementing save method
    @Override
    public void save(Book book) {
        books.add(book);
    }

    // Implementing findById method (this is used for finding a book by isbn)
    public Book findById(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;  // Return the book if isbn matches
            }
        }
        return null;  // Return null if no book is found
    }

    // Implementing update method
    @Override
    public void update(Book book) {
        Book existingBook = findById(book.getIsbn());
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setDescription(book.getDescription());
            existingBook.setNumOfPages(book.getNumOfPages());
            existingBook.setAuthors(book.getAuthors());
        }
    }

    // Implementing delete method
    @Override
    public void delete(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    // Implementing list method to return a List<Book>
    @Override
    public List<Book> list() {
        return books;  // Returning the list of books
    }

    // Implementing find method (now it uses the loop directly)
    @Override
    public Book find(String isbn) {
        // Use loop to find the book matching the isbn value
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;  // Return the book if isbn matches
            }
        }
        return null;  // Return null if no book is found with the matching isbn
    }
}
