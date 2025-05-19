package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    private RestBookDao booksDao = new RestBookDao();

    private BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    @GetMapping("/")
    public String showHome(Model model) {
        // Get current month as integer
        int currentMonth = LocalDate.now().getMonthValue();

        // Pass the currentMonth as string key to list() of bookOfTheMonthDao
        List<BookOfTheMonth> booksOfTheMonth = bookOfTheMonthDao.list(String.valueOf(currentMonth));
        model.addAttribute("booksOfTheMonth", booksOfTheMonth);

        // Build ISBN string like RestBookDao
        StringBuilder isbnBuilder = new StringBuilder();
        String[] isbnKeys = {"ISBN:0451526538", "ISBN:9780140449136", "ISBN:9780141182803"};

        for (int i = 0; i < isbnKeys.length; i++) {
            isbnBuilder.append(isbnKeys[i]);
            if (i < isbnKeys.length - 1) {
                isbnBuilder.append(",");
            }
        }
        String isbnString = isbnBuilder.toString();

        // Call list() on booksDao with isbnString param
        List<Book> books = booksDao.list(isbnString);
        model.addAttribute("books", books);

        model.addAttribute("message", "Welcome to the Book Club!");
        return "index";
    }

    @GetMapping("/about")
    public String showAboutUs(Model model) {
        model.addAttribute("message", "About Us - Learn more about our book club.");
        return "about";
    }

    @GetMapping("/contact")
    public String showContactUs(Model model) {
        model.addAttribute("message", "Contact Us - Get in touch with us.");
        return "contact";
    }

    @GetMapping("/{id}")
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        Book book = booksDao.find(id);

        if (book == null) {
            model.addAttribute("error", "Book not found.");
            return "error";
        }

        model.addAttribute("book", book);
        return "monthly-books/view";
    }
}
