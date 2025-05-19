package com.bookclub.web;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/monthly-books")
public class AdminController {

    @Autowired
    private BookOfTheMonthDao bookOfTheMonthDao;

    /** 1) Show list of Books of the Month **/
    @GetMapping
    public String showBookOfTheMonth(Model model) {
        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list("999");
        model.addAttribute("monthlyBooks", monthlyBooks);
        return "monthly-books/list"; // make sure this template exists
    }

    /** 2) Populate month dropdown **/
    private Map<Integer, String> getMonths() {
        Map<Integer, String> months = new LinkedHashMap<>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
        return months;
    }

    /** 3) Show form to add new Book of the Month **/
    @GetMapping("/new")
    public String bookOfTheMonthForm(Model model) {
        model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
        model.addAttribute("months", getMonths());
        return "monthly-books/new"; // make sure this template exists
    }

    /** 4) Add new Book of the Month **/
    @PostMapping
    public String addBookOfTheMonth(@Valid @ModelAttribute BookOfTheMonth bookOfTheMonth,
                                     BindingResult bindingResult,
                                     Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("months", getMonths());
            return "monthly-books/new";
        }

        bookOfTheMonthDao.add(bookOfTheMonth);
        return "redirect:/monthly-books";
    }

    /** 5) Remove Book of the Month by ID **/
    @GetMapping("/remove/{id}")
    public String removeBookOfTheMonth(@PathVariable("id") String id) {
        bookOfTheMonthDao.remove(id);
        return "redirect:/monthly-books";
    }
}
