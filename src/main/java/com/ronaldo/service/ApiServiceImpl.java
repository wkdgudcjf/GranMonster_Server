package com.ronaldo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.BillingVo;
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
	public boolean registApp(String appName,int companyID,String appURL, String appImagePath,String appPackage) {
		AppVo appVo = new AppVo();
		appVo.setAppName(appName);
		appVo.setCompanyID(companyID);
		appVo.setAppImagePath(appImagePath);
		appVo.setAppURL(appURL);
		appVo.setAppPackage(appPackage);
		appMapper.registApp(appVo);
		return true;
	}
	@Override
	public boolean modifyApp(int appID,String appName,int companyID,String appURL, String appImagePath,String appPackage,boolean appEnable)
	{
		AppVo appVo = new AppVo();
		appVo.setAppID(appID);
		appVo.setAppName(appName);
		appVo.setCompanyID(companyID);
		appVo.setAppImagePath(appImagePath);
		appVo.setAppURL(appURL);
		appVo.setAppPackage(appPackage);
		appVo.setAppEnable(appEnable);
		appMapper.updateApp(appVo);
		return true;
	}
	@Override
	public AppVo getApp(int appID) {
		return appMapper.getApp(appID);
	}
	@Override
	public List<AppVo> getAppList() {
		return appMapper.getAppList();
	}
	
	@Override
	public boolean registCompany(String companyName) {
		CompanyVo companyVo = new CompanyVo();
		companyVo.setCompanyName(companyName);
		companyMapper.registCompany(companyVo);
		return true;
	}
	@Override
	public boolean modifyCompany(int companyID,String companyName,boolean companyEnable)
	{
		CompanyVo companyVo = new CompanyVo();
		companyVo.setCompanyID(companyID);
		companyVo.setCompanyName(companyName);
		companyVo.setCompanyEnable(companyEnable);
		companyMapper.updateCompany(companyVo);
		return true;
	}
	@Override
	public CompanyVo getCompany(int companyID) {
		return companyMapper.getCompany(companyID);
	}
	@Override
	public List<CompanyVo> getCompanyList() {
		return companyMapper.getCompanyList();
	}

	
	@Override
	public boolean registUser(String userKey) {
		UserVo userVo = new UserVo();
		userVo.setUserKey(userKey);
		userVo.setUserEmail("none");
		userVo.setUserPassword("none");
		userVo.setUserMoney(0);
		userVo.setUserCoin(0);
		userMapper.registUser(userVo);
		return true;
	}
	@Override
	public List<UserVo> getUserList() {
		return userMapper.getUserList();
	}
	@Override
	public UserVo getUser(String userKey) {
		return userMapper.getUser(userKey);
	}
	
	@Override
	public boolean registBilling(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<BillingVo> getBillingList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean addBilling(String id, String appId, int coin, int money) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean minusBilling(String id, String appId, int coin, int money) {
		// TODO Auto-generated method stub
		return false;
	}
}
