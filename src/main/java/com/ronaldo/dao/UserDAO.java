package com.ronaldo.dao;

public class UserDAO {
    private String userEmail;
    private int userCoin;
    private int userMoney;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getUserCoin() {
		return userCoin;
	}
	public void setUserCoin(int userCoin) {
		this.userCoin = userCoin;
	}
	public int getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(int userMoney) {
		this.userMoney = userMoney;
	}
	
}