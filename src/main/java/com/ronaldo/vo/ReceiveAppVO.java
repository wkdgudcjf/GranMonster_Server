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
	private MultipartFile appHBannerImage1;
	private MultipartFile appVBannerImage1;
	private MultipartFile appHBannerImage2;
	private MultipartFile appVBannerImage2;
	private MultipartFile appHBannerImage3;
	private MultipartFile appVBannerImage3;
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
	public MultipartFile getAppHBannerImage1() {
		return appHBannerImage1;
	}
	public void setAppHBannerImage1(MultipartFile appHBannerImage1) {
		this.appHBannerImage1 = appHBannerImage1;
	}
	public MultipartFile getAppVBannerImage1() {
		return appVBannerImage1;
	}
	public void setAppVBannerImage1(MultipartFile appVBannerImage1) {
		this.appVBannerImage1 = appVBannerImage1;
	}
	public MultipartFile getAppHBannerImage2() {
		return appHBannerImage2;
	}
	public void setAppHBannerImage2(MultipartFile appHBannerImage2) {
		this.appHBannerImage2 = appHBannerImage2;
	}
	public MultipartFile getAppVBannerImage2() {
		return appVBannerImage2;
	}
	public void setAppVBannerImage2(MultipartFile appVBannerImage2) {
		this.appVBannerImage2 = appVBannerImage2;
	}
	public MultipartFile getAppHBannerImage3() {
		return appHBannerImage3;
	}
	public void setAppHBannerImage3(MultipartFile appHBannerImage3) {
		this.appHBannerImage3 = appHBannerImage3;
	}
	public MultipartFile getAppVBannerImage3() {
		return appVBannerImage3;
	}
	public void setAppVBannerImage3(MultipartFile appVBannerImage3) {
		this.appVBannerImage3 = appVBannerImage3;
	}
	
}