package com.ronaldo.vo;

import com.ronaldo.config.GranConfig.AppTypeEnum;

public class ReceiveInstallVO {
	private String userKey;
	private String appKey;
	private int desAppID;
	private AppTypeEnum appTypeEnum;
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public int getDesAppID() {
		return desAppID;
	}
	public void setDesAppID(int desAppID) {
		this.desAppID = desAppID;
	}
	public AppTypeEnum getAppTypeEnum() {
		return appTypeEnum;
	}
	public void setAppTypeEnum(AppTypeEnum appTypeEnum) {
		this.appTypeEnum = appTypeEnum;
	}
	
}