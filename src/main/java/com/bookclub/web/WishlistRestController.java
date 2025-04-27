package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    private WishlistDao wishlistDao = new com.bookclub.service.impl.MongoWishlistDao();  // (b) instantiate new MongoWishlistDao

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {  // (c) Autowired setter
        this.wishlistDao = wishlistDao;
    }

    @GetMapping
    public List<WishlistItem> showWishlist() {  // (d)
        return wishlistDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)  // (e)
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id);
    }
}
