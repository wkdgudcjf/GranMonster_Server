package com.ronaldo.dao;

public class AppEventDAO {
    private String appEventContent;
	private int appEventCoin;
	private boolean appEventEnable;
	public String getAppEventContent() {
		return appEventContent;
	}
	public void setAppEventContent(String appEventContent) {
		this.appEventContent = appEventContent;
	}
	public int getAppEventCoin() {
		return appEventCoin;
	}
	public void setAppEventCoin(int appEventCoin) {
		this.appEventCoin = appEventCoin;
	}
	public boolean isAppEventEnable() {
		return appEventEnable;
	}
	public void setAppEventEnable(boolean appEventEnable) {
		this.appEventEnable = appEventEnable;
	}
	
}