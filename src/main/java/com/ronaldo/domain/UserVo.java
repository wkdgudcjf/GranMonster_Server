package com.ronaldo.domain;

import java.sql.Timestamp;

public class UserVo {
    private int userID;
    private String userEmail;
    private String userKey;
    private String userPassword;
    private int userCoin;
    private int userMoney;
    private Timestamp userDateTime;
    private boolean userEnable;
    private String userPayload;
	public boolean isUserEnable() {
		return userEnable;
	}
	public void setUserEnable(boolean userEnable) {
		this.userEnable = userEnable;
	}
	public Timestamp getUserDateTime() {
		return userDateTime;
	}
	public void setUserDateTime(Timestamp userDateTime) {
		this.userDateTime = userDateTime;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	public String getUserPayload() {
		return userPayload;
	}
	public void setUserPayload(String userPayload) {
		this.userPayload = userPayload;
	}
	
}