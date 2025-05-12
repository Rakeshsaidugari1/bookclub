package com.bookclub.service.impl;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(WishlistItem item) {
        mongoTemplate.save(item);
    }

    @Override
    public void update(WishlistItem item) {
        mongoTemplate.save(item); // Works for both insert and update
    }

    @Override
    public void save(WishlistItem item) {
        mongoTemplate.save(item);
    }

    @Override
    public boolean remove(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, WishlistItem.class).wasAcknowledged();
    }

    @Override
    public List<WishlistItem> list(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.find(query, WishlistItem.class);
    }

    @Override
    public WishlistItem find(String id) {
        return mongoTemplate.findById(id, WishlistItem.class);
    }

    @Override
    public List<WishlistItem> findAll() {
        return mongoTemplate.findAll(WishlistItem.class);
    }
}
