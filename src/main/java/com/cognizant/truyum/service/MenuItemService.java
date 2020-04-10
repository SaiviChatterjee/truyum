package com.cognizant.truyum.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;

@Service
@Qualifier("menuItemService")
public class MenuItemService {
	@Autowired
	private MenuItemDao menuItemDao;

	public List<MenuItem> getMenuItemListAdmin() {
		try {
			return menuItemDao.getMenuItemListAdmin();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<MenuItem> getMenuItemListCustomer() {
		try {
			return menuItemDao.getMenuItemListCustomer();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public MenuItem getMenuItem(long menuItemId) {
		try {
			return menuItemDao.getMenuItem(menuItemId);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean modifyMenuItem(MenuItem menuItem) {
	try {
		menuItemDao.modifyMenuItem(menuItem);
		return true;
	} catch (ClassNotFoundException | IOException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	}
}
