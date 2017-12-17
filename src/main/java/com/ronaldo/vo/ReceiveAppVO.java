package com.ronaldo.vo;

import org.springframework.web.multipart.MultipartFile;

public class ReceiveAppVO {
	private int appID;
	private boolean appEnable;
	private String appPackage;
	private String appURL;
	private int companyID;
	private String appName;
	private MultipartFile appIconImage;
	private MultipartFile appBannerImage;

	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public boolean isAppEnable() {
		return appEnable;
	}
	public void setAppEnable(boolean appEnable) {
		this.appEnable = appEnable;
	}
	public String getAppPackage() {
		return appPackage;
	}
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
	public String getAppURL() {
		return appURL;
	}
	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public MultipartFile getAppIconImage() {
		return appIconImage;
	}
	public void setAppIconImage(MultipartFile appIconImage) {
		this.appIconImage = appIconImage;
	}
	public MultipartFile getAppBannerImage() {
		return appBannerImage;
	}
	public void setAppBannerImage(MultipartFile appBannerImage) {
		this.appBannerImage = appBannerImage;
	}
}