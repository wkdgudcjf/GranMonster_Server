package com.ronaldo.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.UserInAppVo;

@Mapper
public interface  UserInAppMapper{
	public List<UserInAppVo> getUserInAppByUserID(int userID);
	public UserInAppVo getUserInApp(UserInAppVo userInAppVo);
	public void registUserInApp(UserInAppVo userInAppVo) throws Exception;
}