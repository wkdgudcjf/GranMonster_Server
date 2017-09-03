package com.ronaldo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ronaldo.domain.UserVo;

@Mapper
@Repository
public interface  UserRepository{
	 List<UserVo> getUserList();
}