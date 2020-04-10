/**
 * 
 */
package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

/**
 * @author PRAVEEN
 *
 */
public interface CartDao {
	void addCartItem(long userId, long menuItemId) throws ClassNotFoundException, IOException, SQLException;

	List<MenuItem> getAllCartItems(long userId) throws CartEmptyException, ClassNotFoundException, IOException, SQLException;
	
	void removeCartItem(long userId, long menuItemId) throws ClassNotFoundException, IOException, SQLException;

}
