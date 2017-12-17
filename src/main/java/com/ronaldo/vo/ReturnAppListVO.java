package com.ronaldo.vo;

import java.util.List;

import com.ronaldo.config.ErrorCodeConfig.AppListEnum;

public class ReturnAppListVO {
    private AppListEnum state;
	private List<ReturnAppVO> appList;
	public AppListEnum getState() {
		return state;
	}
	public void setState(AppListEnum state) {
		this.state = state;
	}
	public List<ReturnAppVO> getAppList() {
		return appList;
	}
	public void setAppList(List<ReturnAppVO> appList) {
		this.appList = appList;
	}

    
}