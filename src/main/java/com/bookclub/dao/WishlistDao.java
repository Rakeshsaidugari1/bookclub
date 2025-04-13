package com.bookclub.dao;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.GenericCrudDao;

import org.springframework.stereotype.Repository;

@Repository("wishlistDao")
public interface WishlistDao extends GenericCrudDao<WishlistItem, String> {

	Object findAll();
    // No need to declare deleteById here unless you want a custom method

	void save(WishlistItem wishlistItem);
}
