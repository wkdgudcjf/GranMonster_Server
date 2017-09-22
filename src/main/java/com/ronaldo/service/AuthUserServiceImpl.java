package com.ronaldo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ronaldo.domain.AuthUserVo;
import com.ronaldo.mapper.AuthUserMapper;
@Service
public class AuthUserServiceImpl implements AuthUserService
{ 
	@Autowired 
	AuthUserMapper authUserMapper;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public boolean isVaild(String authUserID, String authUserPassword) {
		AuthUserVo authUserVo = authUserMapper.searchAuthUser(authUserID);
		if(authUserVo==null || !(passwordEncoder.matches(authUserPassword, authUserVo.getAuthUserPassword())))
		{
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public AuthUserVo searchAuthUser(String authUserID) {
		// TODO Auto-generated method stub
		return authUserMapper.searchAuthUser(authUserID);
	}
	@Override
	public boolean createAuthUser(String authUserID, String authUserPassword, String authUserEmail, String inputNumberCheck) {
		if(!inputNumberCheck.equals("ronaldo"))
		{
			return false;
		}
		if(authUserMapper.searchAuthUser(authUserID)!=null)
		{
			return false;
		}
		AuthUserVo authUserVo = new AuthUserVo(authUserID,passwordEncoder.encode(authUserPassword),authUserEmail,"ADMIN");
		authUserMapper.createAuthUser(authUserVo);
		return true;
	} 
}
