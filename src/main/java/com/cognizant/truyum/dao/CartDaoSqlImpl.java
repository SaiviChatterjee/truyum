package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cognizant.truyum.model.MenuItem;

@Repository
@Qualifier("cartDao")
public class CartDaoSqlImpl implements CartDao {

	@Override
	public void addCartItem(long userId, long menuItemId) throws ClassNotFoundException, IOException, SQLException {
		Connection conn = ConnectionHandler.getConnection();
		PreparedStatement smt = conn.prepareStatement("INSERT INTO carts VALUES(?, ?)");
		smt.setLong(1, userId);
		smt.setLong(2, menuItemId);
		smt.executeUpdate();
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId)
			throws CartEmptyException, ClassNotFoundException, IOException, SQLException {
		List<MenuItem> listMenuItem = new ArrayList<>();
		Connection conn = ConnectionHandler.getConnection();
		PreparedStatement smt = conn
				.prepareStatement("SELECT * FROM menuItems INNER JOIN carts ON menuItems.id=carts.menuID WHERE userId = ?");
		smt.setLong(1, userId);
		ResultSet result = smt.executeQuery();
		while (result.next()) {
			MenuItem menuItem = new MenuItem(result.getLong("id"), result.getString("name"), result.getFloat("price"),
					result.getBoolean("active"), result.getDate("dateOfLaunch"), result.getString("category"),
					result.getBoolean("freeDilevery"));
			listMenuItem.add(menuItem);
		}
		if(listMenuItem.isEmpty())
			throw new CartEmptyException();
		return listMenuItem;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) throws ClassNotFoundException, IOException, SQLException {

		Connection conn = ConnectionHandler.getConnection();
		PreparedStatement smt = conn.prepareStatement("DELETE FROM carts WHERE userID=? AND menuId=?");
		smt.setLong(1, userId);
		smt.setLong(2, menuItemId);
		smt.executeUpdate();
	}

}
