package com.ronaldo.vo;

public class ReceiveAppEventVO {
	private int appEventID;
	private int appID;
	private String appEventContent;
	private String appEventKey;
	private int appEventCoin;
	private String appEventStartTime;
	private String appEventEndTime;
	private int appEventLimit;
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
	public String getAppEventKey() {
		return appEventKey;
	}
	public void setAppEventKey(String appEventKey) {
		this.appEventKey = appEventKey;
	}
	public int getAppEventCoin() {
		return appEventCoin;
	}
	public void setAppEventCoin(int appEventCoin) {
		this.appEventCoin = appEventCoin;
	}
	public String getAppEventStartTime() {
		return appEventStartTime;
	}
	public void setAppEventStartTime(String appEventStartTime) {
		this.appEventStartTime = appEventStartTime;
	}
	public String getAppEventEndTime() {
		return appEventEndTime;
	}
	public void setAppEventEndTime(String appEventEndTime) {
		this.appEventEndTime = appEventEndTime;
	}
	public int getAppEventLimit() {
		return appEventLimit;
	}
	public void setAppEventLimit(int appEventLimit) {
		this.appEventLimit = appEventLimit;
	}
	public boolean isAppEventEnable() {
		return appEventEnable;
	}
	public void setAppEventEnable(boolean appEventEnable) {
		this.appEventEnable = appEventEnable;
	}
	
}