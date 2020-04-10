package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cognizant.truyum.model.MenuItem;

@Repository
@Qualifier("menuItemDao")
public class MenuItemDaoSqlImpl implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() throws ClassNotFoundException, IOException, SQLException {
		List<MenuItem> menuItemList = new ArrayList<>();
		Connection conn = ConnectionHandler.getConnection();
		PreparedStatement smt = conn.prepareStatement("SELECT * FROM menuItems");
		ResultSet result = smt.executeQuery();
		while (result.next()) {
			MenuItem menuItem = new MenuItem(result.getLong("id"), result.getString("name"), result.getFloat("price"),
					result.getBoolean("active"), result.getDate("dateOfLaunch"), result.getString("category"),
					result.getBoolean("freeDilevery"));
			menuItemList.add(menuItem);
		}
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() throws ClassNotFoundException, IOException, SQLException {
		List<MenuItem> menuItemList = new ArrayList<>();
		Connection conn = ConnectionHandler.getConnection();
		PreparedStatement smt = conn.prepareStatement("SELECT * FROM menuItems where active=? AND dateOfLaunch<?");
		smt.setBoolean(1, true);
		smt.setDate(2, new java.sql.Date(new Date().getTime()));
		ResultSet result = smt.executeQuery();
		while (result.next()) {
			MenuItem menuItem = new MenuItem(result.getLong("id"), result.getString("name"), result.getFloat("price"),
					result.getBoolean("active"), result.getDate("dateOfLaunch"), result.getString("category"),
					result.getBoolean("freeDilevery"));
			menuItemList.add(menuItem);
		}

		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) throws ClassNotFoundException, IOException, SQLException {
		Connection conn = ConnectionHandler.getConnection();
		PreparedStatement smt = conn.prepareStatement(
				"UPDATE menuItems SET name=?, price=?, active=?, dateOfLaunch=?, category=?, freeDilevery=? WHERE id=?");
		smt.setString(1, menuItem.getName());
		smt.setFloat(2, menuItem.getPrice());
		smt.setBoolean(3, menuItem.isActive());
		smt.setDate(4, new java.sql.Date(menuItem.getDateOfLaunch().getTime()));
		smt.setString(5, menuItem.getCategory());
		smt.setBoolean(6, menuItem.isFreeDelivery());
		smt.setLong(7, menuItem.getId());
		smt.executeUpdate();
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) throws ClassNotFoundException, IOException, SQLException {
		Connection conn = ConnectionHandler.getConnection();
		PreparedStatement smt = conn.prepareStatement("SELECT * FROM menuItems WHERE id=?");
		smt.setLong(1, menuItemId);
		ResultSet result = smt.executeQuery();
		while (result.next()) {
			return new MenuItem(result.getLong("id"), result.getString("name"), result.getFloat("price"),
					result.getBoolean("active"), result.getDate("dateOfLaunch"), result.getString("category"),
					result.getBoolean("freeDilevery"));
		}
		return null;
	}

}
