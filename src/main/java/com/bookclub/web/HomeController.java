package com.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("message", "Welcome to the Book Club!");
        return "index";  // This should match src/main/resources/templates/index.html
    }

    @GetMapping("/about")
    public String showAboutUs(Model model) {
        model.addAttribute("message", "About Us - Learn more about our book club.");
        return "about";  // This should match src/main/resources/templates/about.html
    }

    @GetMapping("/contact")
    public String showContactUs(Model model) {
        model.addAttribute("message", "Contact Us - Get in touch with us.");
        return "contact";  // This should match src/main/resources/templates/contact.html
    }
}
