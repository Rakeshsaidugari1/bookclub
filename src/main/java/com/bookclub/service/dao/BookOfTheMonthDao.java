package com.bookclub.service.dao;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.GenericCrudDao;

public interface BookOfTheMonthDao extends GenericCrudDao<BookOfTheMonth, String> {
    // No need to define methods explicitly – inherited from GenericCrudDao
}
