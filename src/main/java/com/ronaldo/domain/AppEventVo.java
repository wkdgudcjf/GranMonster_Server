package com.ronaldo.domain;


public class AppEventVo {
    private int appEventID;
    private int appID;
    private String appEventContent;
	private int appEventKey;
	private int appEventCoin;
	private boolean appEventEnable;
	public int getAppEventID() {
		return appEventID;
	}
	public void setAppEventID(int appEventID) {
		this.appEventID = appEventID;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getAppEventContent() {
		return appEventContent;
	}
	public void setAppEventContent(String appEventContent) {
		this.appEventContent = appEventContent;
	}
	public int getAppEventKey() {
		return appEventKey;
	}
	public void setAppEventKey(int appEventKey) {
		this.appEventKey = appEventKey;
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