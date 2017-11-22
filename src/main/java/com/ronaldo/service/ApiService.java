package com.ronaldo.service;

import java.util.List;

import com.ronaldo.domain.AppEventVo;
import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.BillingVo;
import com.ronaldo.domain.CompanyVo;
import com.ronaldo.domain.ExchangeVo;
import com.ronaldo.domain.UserInAppVo;
import com.ronaldo.domain.UserVo;

public interface ApiService
{
	public boolean registApp(String appName,int companyID,String appURL, String appImagePath,String appPackage);
	public List<AppVo> getAppList();
	public List<AppVo> getEnableAppList(boolean appEnable);
	public AppVo getApp(int appID);
	public boolean modifyApp(int appID,String appName,int companyID,String appURL, String appImagePath,String appPackage,boolean appEnable);
	public String setUserPayload(String appKey, String userKey);
	public String getUserPayload(String userKey);
	
	public boolean registCompany(String companyName);
	public List<CompanyVo> getCompanyList();
	public CompanyVo getCompany(int companyID);
	public boolean modifyCompany(int companyID,String companyName,boolean companyEnable);
	
	public List<ExchangeVo> getExchangeList();
	public boolean modifyExchange(int exchangeID, int exchangeMoney, int exchangeCoin, boolean exchangeEnable);
	public ExchangeVo getExchange(int exchangeID);
	public boolean registExchange(int exchangeMoney, int exchangeCoin);
	
	public boolean registUser(String userKey);
	public boolean registUserInApp(String userKey,String appKey);
	public List<UserVo> getUserList();
	public UserVo getUser(String userKey);
	public UserVo getUser(int userID);
	public List<UserInAppVo> getUserInAppByUserID(int userID);
	
	public List<BillingVo> getBillingList();
	public List<BillingVo> getUserBillingList(String userKey);
	public List<BillingVo> getAppBillingList(String appID);
	public boolean addBilling(String userKey,String appKey,int billingCoin,int billingMoney,boolean billingType);
	public boolean minusBilling(String userKey,String appKey,int billingCoin,boolean billingType);
	
	public List<AppEventVo> getAppEventList(int appID);
	public AppEventVo getAppEvent(int appEventID);
	public boolean registAppEvent(int appID,String appEventContent,int appEventCoin);
	
}
