package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.AppEventVo;

@Mapper
public interface  UserEventMapper{
	 public List<AppEventVo> getAppEventList();
	 public List<AppEventVo> getAppEventByAppID(int appID);
	 public AppEventVo getAppEventByEventID(int appEventID);
	 public void registAppEvent(AppEventVo appEventVo) throws Exception;
	 public void updateAppEvent(AppEventVo appEventVo) throws Exception;
}