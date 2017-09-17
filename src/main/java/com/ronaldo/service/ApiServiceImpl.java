package com.ronaldo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.CompanyVo;
import com.ronaldo.domain.UserVo;
import com.ronaldo.mapper.BillingMapper;
import com.ronaldo.mapper.AppMapper;
import com.ronaldo.mapper.CompanyMapper;
import com.ronaldo.mapper.UserMapper;
@Service
public class ApiServiceImpl implements ApiService
{ 
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private AppMapper appMapper;
	
	@Autowired
	private BillingMapper billingMapper;

	@Override
	public boolean login(String id, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean join(UserVo userVo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registApp(AppVo companyAppVo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registCompany(CompanyVo companyVo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean AddBilling(String id, String appId, int coin, int money) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean MinusBilling(String id, String appId, int coin, int money) {
		// TODO Auto-generated method stub
		return false;
	}
}
