package com.ronaldo.domain;

import java.sql.Timestamp;

public class AppEventVo {
    private int appEventID;
    private int appID;
    private String appEventContent;
	private String appEventKey;
	private int appEventCoin;
	private Timestamp appEventStartTime;
	private Timestamp appEventEndTime;
	private int appEventLimit;
	private boolean appEventEnable;
	
	public String getAppEventKey() {
		return appEventKey;
	}
	public void setAppEventKey(String appEventKey) {
		this.appEventKey = appEventKey;
	}
	public Timestamp getAppEventStartTime() {
		return appEventStartTime;
	}
	public void setAppEventStartTime(Timestamp appEventStartTime) {
		this.appEventStartTime = appEventStartTime;
	}
	public Timestamp getAppEventEndTime() {
		return appEventEndTime;
	}
	public void setAppEventEndTime(Timestamp appEventEndTime) {
		this.appEventEndTime = appEventEndTime;
	}
	public int getAppEventLimit() {
		return appEventLimit;
	}
	public void setAppEventLimit(int appEventLimit) {
		this.appEventLimit = appEventLimit;
	}
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