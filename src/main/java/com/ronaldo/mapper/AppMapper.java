package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.AppVo;

@Mapper
public interface  AppMapper{
	public AppVo getAppByID(int appID);
	public AppVo getAppByKey(String appKey);
	 public List<AppVo> getAppList();
	 public List<AppVo> getEnableAppList(boolean appEnable);
	 public void registApp(AppVo appVo) throws Exception;
	 public void deleteApp(int appID) throws Exception;
	 public void updateApp(AppVo appVo) throws Exception;
	 public void modifyAppKey(AppVo appVo) throws Exception;
}