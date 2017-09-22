package com.ronaldo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.AuthUserVo;

@Mapper
public interface  AuthUserMapper{
	public AuthUserVo searchAuthUser(String authUserID); 
	//public List<GrantedAuthority> readAuthority(String username);
	public void createAuthUser(AuthUserVo authUserVo);
	//public void createAuthority(AuthUserVo user);
	public void deleteAuthUser(String authUserID);
	//public void deleteAuthority(String username);
}