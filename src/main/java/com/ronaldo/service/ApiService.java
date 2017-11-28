package com.ronaldo.service;

import java.sql.Timestamp;
import java.util.List;

import com.ronaldo.domain.AppEventVo;
import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.BillingVo;
import com.ronaldo.domain.CompanyVo;
import com.ronaldo.domain.ExchangeVo;
import com.ronaldo.domain.UserEventVo;
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
	boolean modifyExchange(int exchangeID, int exchangeMoney, int exchangeCoin, boolean exchangeEnable,
			String exchangeName,String exchangeKey, String exchangeImagePath);
	public ExchangeVo getExchange(int exchangeID);
	boolean registExchange(int exchangeMoney, int exchangeCoin, String exchangeName,String exchangeKey, String exchangeImagePath);
	public List<ExchangeVo> getEnableExchangeList(boolean exchangeEnable);
	
	public boolean registUser(String userKey);
	public boolean registUserInApp(String userKey,String appKey);
	public List<UserVo> getUserList();
	public UserVo getUser(String userKey);
	public UserVo getUser(int userID);
	public List<UserInAppVo> getUserInAppByUserID(int userID);
	public UserInAppVo getUserInApp(String userKey, String appKey);
	
	public List<BillingVo> getBillingList();
	public List<BillingVo> getUserBillingList(String userKey);
	public List<BillingVo> getAppBillingList(String appID);
	public boolean addBilling(String userKey,String appKey,int billingCoin,int billingMoney,String billingType);
	public boolean addBilling(int userID, int appID, int billingCoin, int billingMoney, String billingType);
	public boolean minusBilling(String userKey,String appKey,int billingCoin,String billingType);
	
	public List<AppEventVo> getAppEventList(int appID);
	public AppEventVo getAppEvent(int appEventID);
	public AppEventVo getAppEvent(int appID,String appEventKey);
	public boolean registAppEvent(int appID,String appEventContent,int appEventCoin, Timestamp appEventStartTime, Timestamp appEventEndTime, String appEventKey, int appEventLimit);
	boolean modifyAppEvent(int appEventID, String appEventContent, int appEventCoin, boolean appEventEnable,Timestamp appEventStartTime, Timestamp appEventEndTime, String appEventKey, int appEventLimit);	
	
	public boolean registUserEvent(int userID, int appEventID);
	public UserEventVo getUserEvent(int userID, int appEventID);
	public List<UserEventVo> getUserEventList(int userID);
}
