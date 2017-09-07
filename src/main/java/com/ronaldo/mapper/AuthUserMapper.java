package com.ronaldo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.AuthUserVo;

@Mapper
public interface  AuthUserMapper{
	public AuthUserVo searchUser(String username); 
	//public List<GrantedAuthority> readAuthority(String username);
	public void createUser(AuthUserVo user);
	//public void createAuthority(AuthUserVo user);
	public void deleteUser(String username);
	//public void deleteAuthority(String username);
}