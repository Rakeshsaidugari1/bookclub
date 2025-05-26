package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistDao wishlistDao;

    // ✅ Redirect root /wishlist to /wishlist/list
    @GetMapping
    public String redirectToList() {
        return "redirect:/wishlist/list";
    }

    // ✅ List all items for the logged-in user
    @GetMapping("/list")
    public String showWishlist(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("wishlist", wishlistDao.list(username));
        return "wishlist/list";
    }

    // ✅ Show a specific wishlist item (handles null case)
    @GetMapping("/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem item = wishlistDao.find(id);
        if (item == null) {
            model.addAttribute("error", "Wishlist item not found.");
            return "error"; // Make sure templates/error.html exists
        }
        model.addAttribute("wishlistItem", item);
        return "wishlist/view";
    }

    // ✅ Update a wishlist item
    @PostMapping("/update")
    public String updateWishlistItem(
            @Validated @ModelAttribute WishlistItem wishlistItem,
            BindingResult bindingResult,
            Authentication authentication) {

        if (authentication != null) {
            wishlistItem.setUsername(authentication.getName());
        }

        if (bindingResult.hasErrors()) {
            return "wishlist/view";
        }

        wishlistDao.update(wishlistItem);
        return "redirect:/wishlist/list";
    }

    // ✅ Remove a wishlist item
    @PostMapping("/remove/{id}")
    public String removeWishlistItem(@PathVariable String id) {
        wishlistDao.remove(id);
        return "redirect:/wishlist/list";
    }
}
