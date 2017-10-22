package com.ronaldo.dao;

public class ReturnUserDAO {
    private String state;
    private UserDAO userDAO;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}