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
	public boolean isVaild(String id, String password) {
		AuthUserVo user = authUserMapper.searchUser(id);
		if(user==null || !(passwordEncoder.matches(password, user.getPassword())))
		{
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public AuthUserVo searchUser(String id) {
		// TODO Auto-generated method stub
		return authUserMapper.searchUser(id);
	}
	@Override
	public boolean createUser(String id, String password, String email, String inputNumberCheck) {
		if(!inputNumberCheck.equals("ronaldo"))
		{
			return false;
		}
		if(authUserMapper.searchUser(id)!=null)
		{
			return false;
		}
		AuthUserVo user = new AuthUserVo(id,passwordEncoder.encode(password),email,"ADMIN");
		authUserMapper.createUser(user);
		return true;
	} 
}
