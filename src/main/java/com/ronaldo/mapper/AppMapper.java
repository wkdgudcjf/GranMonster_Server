package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.AppVo;

@Mapper
public interface  AppMapper{
	public AppVo getApp(int appID);
	 public List<AppVo> getAppList();
	 public List<AppVo> getEnableAppList(boolean appEnable);
	 public void registApp(AppVo appVo);
	 public void deleteApp(int appID);
	 public void updateApp(AppVo appVo);
}