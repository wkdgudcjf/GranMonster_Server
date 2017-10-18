package com.ronaldo.domain;


public class UserEventVo {
    private int userEventID;
    private int userID;
    private int appEventID;
	public int getUserEventID() {
		return userEventID;
	}
	public void setUserEventID(int userEventID) {
		this.userEventID = userEventID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAppEventID() {
		return appEventID;
	}
	public void setAppEventID(int appEventID) {
		this.appEventID = appEventID;
	}
    
}