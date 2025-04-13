package com.bookclub.model;

import org.springframework.data.annotation.Id;

public class WishlistItem {

    @Id
    private String id;

    private String isbn;
    private String title;
    private String author;

    // Constructors
    public WishlistItem() {}

    public WishlistItem(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Updated toString method
    @Override
    public String toString() {
        return String.format("WishlistItem{id=%s, isbn=%s, title=%s}", id, isbn, title);
    }
}
