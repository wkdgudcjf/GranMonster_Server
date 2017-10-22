package com.ronaldo.dao;

import java.util.List;

public class AppListDAO {
    private String state;
	private List<AppDAO> appList;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<AppDAO> getAppList() {
		return appList;
	}
	public void setAppList(List<AppDAO> appList) {
		this.appList = appList;
	}

    
}