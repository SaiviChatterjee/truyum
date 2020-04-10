package com.cognizant.truyum.controller;

import javax.sql.rowset.serial.SerialArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam long menuItemId, RedirectAttributes ra) {
		ra.addAttribute("addCartStatus", cartService.addCartItem(1, menuItemId));
		return "redirect:show-menu-list-customer";
	}

	@GetMapping("/show-cart")
	public String showCart(@RequestParam(required = false) boolean removeCartItemStatus, @RequestParam long userId,
			ModelMap model) {
		Cart cart = cartService.getAllCartItems(userId);
		if (cart == null)
			return "cart-empty";
		model.put("userId", userId);
		model.put("removeCartItemStatus", removeCartItemStatus);
		model.put("cart", cart);
		return "cart";
	}

	@GetMapping("remove-cart-item")
	public String removeCart(@RequestParam long userId, @RequestParam long menuItemId, RedirectAttributes ra) {
		ra.addAttribute("userId", userId);
		ra.addAttribute("removeCartItemStatus", cartService.removeCart(1, menuItemId));
		return "redirect:show-cart";
	}
}
