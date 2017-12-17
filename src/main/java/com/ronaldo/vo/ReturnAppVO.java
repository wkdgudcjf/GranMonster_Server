package com.ronaldo.vo;

import java.util.List;

public class ReturnAppVO {
	private String appName;
	private String appKey;
	private String appURL;
	private String appImageIconPath;
	private String appImageBannerPath;
	private String appPackage;
	private boolean appInstall;
	private List<ReturnAppEventVO> appEventList;
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppURL() {
		return appURL;
	}
	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}
	public String getAppImageIconPath() {
		return appImageIconPath;
	}
	public void setAppImageIconPath(String appImageIconPath) {
		this.appImageIconPath = appImageIconPath;
	}
	public String getAppImageBannerPath() {
		return appImageBannerPath;
	}
	public void setAppImageBannerPath(String appImageBannerPath) {
		this.appImageBannerPath = appImageBannerPath;
	}
	public String getAppPackage() {
		return appPackage;
	}
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
	
	public boolean isAppInstall() {
		return appInstall;
	}
	public void setAppInstall(boolean appInstall) {
		this.appInstall = appInstall;
	}
	public List<ReturnAppEventVO> getAppEventList() {
		return appEventList;
	}
	public void setAppEventList(List<ReturnAppEventVO> appEventList) {
		this.appEventList = appEventList;
	}
	
}