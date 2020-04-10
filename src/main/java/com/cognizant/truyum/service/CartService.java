package com.cognizant.truyum.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@Service
@Qualifier("cartService")
public class CartService {
	@Autowired
	private CartDao cartDao;

	public boolean addCartItem(long userId, long menuItemId) {
		try {
			cartDao.addCartItem(userId, menuItemId);
			return true;
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Cart getAllCartItems(long userID) {
		try {
			List<MenuItem> menuItemList = cartDao.getAllCartItems(userID);
			double total= menuItemList.parallelStream().mapToDouble(m -> m.getPrice() ).sum();
			Cart cart = new Cart(menuItemList, total);
			return cart;
		} catch (ClassNotFoundException | CartEmptyException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean removeCart(long userId, long menuItemId) {
		try {
			cartDao.removeCartItem(userId, menuItemId);
			return true;
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
