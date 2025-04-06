package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao {

    // Simulated in-memory storage
    private static final List<WishlistItem> wishlist = new ArrayList<>();

    // Save a new item
    public void save(WishlistItem item) {
        wishlist.add(item);
    }

    // Retrieve the list of items
    public List<WishlistItem> list() {
        return new ArrayList<>(wishlist); // Return a copy to avoid modification outside
    }
}
