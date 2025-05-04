package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(Model model) {
        RestBookDao booksDao = new RestBookDao();
        List<Book> books = booksDao.list();
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
        RestBookDao booksDao = new RestBookDao();
        Book book = booksDao.find(id);
        
        if (book == null) {
            model.addAttribute("error", "Book not found.");
            return "error"; // Make sure error.html exists
        }

        model.addAttribute("book", book);
        return "monthly-books/view"; // Ensure this template handles 'book' safely
    }
}
