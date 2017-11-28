package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.AppEventVo;

@Mapper
public interface  AppEventMapper{
	 public List<AppEventVo> getAppEventList();
	 public List<AppEventVo> getAppEventByAppID(int appID);
	 public AppEventVo getAppEventByEventID(int appEventID);
	 public AppEventVo getAppEventByAppIDByEventKey(AppEventVo appEventVo);
	 public void registAppEvent(AppEventVo appEventVo) throws Exception;
	 public void updateAppEvent(AppEventVo appEventVo) throws Exception;
}