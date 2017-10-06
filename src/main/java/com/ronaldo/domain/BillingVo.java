package com.ronaldo.domain;

import java.sql.Timestamp;

public class BillingVo {
    private int billingID;
    private int userID;
    private int appID;
    private int billingMoney;
    private int billingCoin;
    private int appEventKey;
    private String appName;
    private String userKey;
    private String appEventContent;
	private Timestamp billingDateTime;

	public int getAppEventKey() {
		return appEventKey;
	}
	public void setAppEventKey(int appEventKey) {
		this.appEventKey = appEventKey;
	}
	public String getAppEventContent() {
		return appEventContent;
	}
	public void setAppEventContent(String appEventContent) {
		this.appEventContent = appEventContent;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public int getBillingID() {
		return billingID;
	}
	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}
	
	public int getBillingMoney() {
		return billingMoney;
	}
	public void setBillingMoney(int billingMoney) {
		this.billingMoney = billingMoney;
	}
	public int getBillingCoin() {
		return billingCoin;
	}
	public void setBillingCoin(int billingCoin) {
		this.billingCoin = billingCoin;
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Timestamp getBillingDateTime() {
		return billingDateTime;
	}
	public void setBillingDateTime(Timestamp billingDateTime) {
		this.billingDateTime = billingDateTime;
	}
   
}