package com.bookclub.model;

import org.springframework.data.annotation.Id;

public class WishlistItem {

    @Id
    private String id;

    private String isbn;
    private String title;
    private String author;

    private String username; // ✅ New field

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

    // ✅ New getter and setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ✅ Updated toString method
    @Override
    public String toString() {
        return String.format("WishlistItem{id=%s, isbn=%s, title=%s, author=%s, username=%s}",
                id, isbn, title, author, username);
    }
}
