package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.UserEventVo;

@Mapper
public interface  UserEventMapper{
	public UserEventVo getUserEvent(UserEventVo userEvent);
	public void registUserEvent(UserEventVo userEvent);
	public List<UserEventVo> getUserEventList(int userID);
}