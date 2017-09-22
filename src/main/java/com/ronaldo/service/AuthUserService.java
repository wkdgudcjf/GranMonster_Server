package com.ronaldo.service;

import com.ronaldo.domain.AuthUserVo;

public interface AuthUserService
{ 
	public boolean isVaild(String id,String password);
	public boolean createAuthUser(String id, String password, String email, String inputNumberCheck);
	public AuthUserVo searchAuthUser(String id);
}
