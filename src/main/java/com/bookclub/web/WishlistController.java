package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.dao.WishlistDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

	private final WishlistDao wishlistDao;

	public WishlistController(WishlistDao wishlistDao) {
		this.wishlistDao = wishlistDao;
	}

	// Show the list of wishlist items
	@GetMapping("/list")
	public String showWishlist(Model model) {
		model.addAttribute("wishlist", wishlistDao.findAll());
		return "wishlist/list";
	}

//  Show the form to add a new wishlist item
	@GetMapping("/new")
	public String wishlistForm(Model model) {
		model.addAttribute("wishlistItem", new WishlistItem());
		return "wishlist/new";
	}

	
	// Add a new wishlist item
	@PostMapping("/add")
	public String addWishlistItem(@Validated WishlistItem wishlistItem, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "wishlist/new";
		}
		wishlistDao.save(wishlistItem); // Save the new wishlist item using JPA
		return "redirect:/wishlist/list"; // Redirect to wishlist page after adding item
	}
}
