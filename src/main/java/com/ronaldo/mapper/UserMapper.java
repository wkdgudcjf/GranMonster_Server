package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.UserVo;

@Mapper
public interface  UserMapper{
	 public List<UserVo> getUserList();
	 public void registUser(UserVo userVo);
	 public UserVo getUser(String userKey);
	 public void deleteUser(int userID);
	 public void UpdateUser(UserVo userVo);
}