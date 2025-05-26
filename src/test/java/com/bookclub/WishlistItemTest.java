package com.bookclub;

import org.junit.jupiter.api.Test;

import com.bookclub.model.WishlistItem;

import static org.junit.jupiter.api.Assertions.*;

class WishlistItemTest {

    @Test
    void testWishlistItemSettersAndGetters() {
        WishlistItem item = new WishlistItem();

        item.setIsbn("9780132350884");
        item.setTitle("Clean Code");
        item.setAuthor("Robert C. Martin");
        item.setUsername("alice");

        assertNull(item.getId());  // ID is not set manually
        assertEquals("9780132350884", item.getIsbn());
        assertEquals("Clean Code", item.getTitle());
        assertEquals("Robert C. Martin", item.getAuthor());
        assertEquals("alice", item.getUsername());
    }

    @Test
    void testWishlistItemConstructorAndToString() {
        WishlistItem item = new WishlistItem("9780132350884", "Clean Code", "Robert C. Martin");
        item.setUsername("bob");

        String result = item.toString();
        assertTrue(result.contains("isbn=9780132350884"));
        assertTrue(result.contains("title=Clean Code"));
        assertTrue(result.contains("author=Robert C. Martin"));
        assertTrue(result.contains("username=bob"));
    }
}
