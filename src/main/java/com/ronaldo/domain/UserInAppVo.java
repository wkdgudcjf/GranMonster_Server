package com.ronaldo.domain;

import java.util.List;

public class UserInAppVo {
    private int userInAppID;
    private int userID;
    private int appID;
    private String appName;
    private List<AppEventVo> appEventList;
    
	public List<AppEventVo> getAppEventList() {
		return appEventList;
	}
	public void setAppEventList(List<AppEventVo> appEventList) {
		this.appEventList = appEventList;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getUserInAppID() {
		return userInAppID;
	}
	public void setUserInAppID(int userInAppID) {
		this.userInAppID = userInAppID;
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

	
}