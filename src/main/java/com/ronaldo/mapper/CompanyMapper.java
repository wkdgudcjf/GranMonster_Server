package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.UserVo;

@Mapper
public interface  CompanyMapper{
	 List<UserVo> getUserList();
}