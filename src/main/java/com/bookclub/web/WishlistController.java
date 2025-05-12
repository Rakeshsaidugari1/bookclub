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

    // ✅ List all items for the logged-in user
    @GetMapping("/list")
    public String showWishlist(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("wishlist", wishlistDao.list(username));
        return "wishlist/list";
    }

    // ✅ a. Show a specific wishlist item
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem item = wishlistDao.find(id);
        model.addAttribute("wishlistItem", item);
        return "wishlist/view";
    }

    // ✅ b. Update a wishlist item
    @RequestMapping(method = RequestMethod.POST, path = "/update")
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

    // ✅ c. Remove a wishlist item
    @RequestMapping(method = RequestMethod.POST, path = "/remove/{id}")
    public String removeWishlistItem(@PathVariable String id) {
        wishlistDao.remove(id);
        return "redirect:/wishlist/list";
    }
}
