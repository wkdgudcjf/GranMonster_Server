package com.ronaldo.service;

import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.CompanyVo;
import com.ronaldo.domain.UserVo;

public interface ApiService
{ 
	public boolean login(String id,String password);
	public boolean join(UserVo userVo);
	public boolean registApp(AppVo companyAppVo);
	public boolean registCompany(CompanyVo companyVo);
	public boolean AddBilling(String id,String appId,int coin,int money);
	public boolean MinusBilling(String id,String appId,int coin,int money);
}
