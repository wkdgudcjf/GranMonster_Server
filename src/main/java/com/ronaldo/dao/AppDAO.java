package com.ronaldo.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ronaldo.domain.AppEventVo;

public class AppDAO {
	private String appName;
	private String appURL;
	private String appImagePath;
	private String appPackage;
	private boolean appInstall;
	private List<AppEventVo> appEventList;
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
	public String getAppImagePath() {
		return appImagePath;
	}
	public void setAppImagePath(String appImagePath) {
		this.appImagePath = appImagePath;
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
	public List<AppEventVo> getAppEventList() {
		return appEventList;
	}
	public void setAppEventList(List<AppEventVo> appEventList) {
		this.appEventList = appEventList;
	}
	
}