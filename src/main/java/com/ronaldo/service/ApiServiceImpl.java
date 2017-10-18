package com.ronaldo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ronaldo.domain.AppEventVo;
import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.BillingVo;
import com.ronaldo.domain.CompanyVo;
import com.ronaldo.domain.UserInAppVo;
import com.ronaldo.domain.UserVo;
import com.ronaldo.mapper.BillingMapper;
import com.ronaldo.mapper.AppEventMapper;
import com.ronaldo.mapper.AppMapper;
import com.ronaldo.mapper.CompanyMapper;
import com.ronaldo.mapper.UserEventMapper;
import com.ronaldo.mapper.UserInAppMapper;
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
	
	@Autowired
	private AppEventMapper appEventMapper;
	
	@Autowired
	private UserInAppMapper userInAppMapper;

	@Autowired
	private UserEventMapper userEventMapper;


	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public boolean registApp(String appName,int companyID,String appURL, String appImagePath,String appPackage) {
		AppVo appVo = new AppVo();
		appVo.setAppName(appName);
		appVo.setCompanyID(companyID);
		appVo.setAppImagePath(appImagePath);
		appVo.setAppURL(appURL);
		appVo.setAppPackage(appPackage);
		try
		{
			appMapper.registApp(appVo);
			appVo.setAppKey(passwordEncoder.encode(String.valueOf(appVo.getAppID())));
			appMapper.modifyAppKey(appVo);
			AppEventVo appEventVo1 = new AppEventVo();
			appEventVo1.setAppEventContent("충전");
			appEventVo1.setAppEventKey(1);
			appEventVo1.setAppID(appVo.getAppID());
			appEventVo1.setAppEventCoin(0);
			appEventMapper.registAppEvent(appEventVo1);
			AppEventVo appEventVo2 = new AppEventVo();
			appEventVo2.setAppEventContent("사용");
			appEventVo2.setAppEventKey(2);
			appEventVo2.setAppID(appVo.getAppID());
			appEventVo2.setAppEventCoin(0);
			appEventMapper.registAppEvent(appEventVo2);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
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
		try
		{
			appMapper.updateApp(appVo);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public AppVo getApp(int appID) {
		return appMapper.getAppByID(appID);
	}
	@Override
	public List<AppVo> getAppList() {
		return appMapper.getAppList();
	}
	
	@Override
	public boolean registCompany(String companyName) {
		CompanyVo companyVo = new CompanyVo();
		companyVo.setCompanyName(companyName);
		try
		{
			companyMapper.registCompany(companyVo);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean modifyCompany(int companyID,String companyName,boolean companyEnable)
	{
		CompanyVo companyVo = new CompanyVo();
		companyVo.setCompanyID(companyID);
		companyVo.setCompanyName(companyName);
		companyVo.setCompanyEnable(companyEnable);
		try
		{
			companyMapper.updateCompany(companyVo);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
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
		try
		{
			userMapper.registUser(userVo); // 회원가입.
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean registUserInApp(String userKey,String appKey) {
		try
		{
			UserInAppVo userInAppVo = new UserInAppVo();
			userInAppVo.setAppID(appMapper.getAppByKey(appKey).getAppID());
			userInAppVo.setUserID(userMapper.getUser(userKey).getUserID());
			//여기서 그 유저가 이 앱에 로그인이 되어 있다면?
			userInAppMapper.registUserInApp(userInAppVo);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public UserInAppVo getUserInApp(String userKey, String appKey) {
		// TODO Auto-generated method stub
		UserInAppVo userInAppVo = new UserInAppVo();
		userInAppVo.setUserID(userMapper.getUser(userKey).getUserID());
		userInAppVo.setAppID(appMapper.getAppByKey(appKey).getAppID());
		return userInAppMapper.getUserInApp(userInAppVo);
	}
	public List<UserInAppVo> getUserInAppByUserID(int userID) {
		// TODO Auto-generated method stub
		return userInAppMapper.getUserInAppByUserID(userID);
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
	public UserVo getUser(int userID) {
		return userMapper.getUserByUserID(userID);
	}
	@Override
	public List<BillingVo> getBillingList() {
		return billingMapper.getBillingList();
	}
	@Override
	public boolean addBilling(String userKey, String appKey, int billingCoin, int billingMoney,int appEventKey) {
		// TODO Auto-generated method stub
		BillingVo billingVo = new BillingVo();
		billingVo.setUserID(userMapper.getUser(userKey).getUserID());
		billingVo.setAppID(appMapper.getAppByKey(appKey).getAppID());
		billingVo.setBillingCoin(billingCoin);
		billingVo.setBillingMoney(billingMoney);
		billingVo.setAppEventKey(appEventKey);
		try
		{
			billingMapper.addBilling(billingVo);
			UserVo userVo = userMapper.getUser(userKey);
			userVo.setUserCoin(userVo.getUserCoin() + billingCoin);
			userVo.setUserMoney(userVo.getUserMoney() + billingMoney);
			userMapper.updateUser(userVo);
			//여기서 User Coin도 올려줌.
			//event 분기해서 이벤트도 올려줌.
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean minusBilling(String userKey, String appKey, int billingCoin,int appEventKey) {
		BillingVo billingVo = new BillingVo();
		billingVo.setUserID(userMapper.getUser(userKey).getUserID());
		billingVo.setAppID(appMapper.getAppByKey(appKey).getAppID());
		billingVo.setBillingCoin(billingCoin);
		billingVo.setAppEventKey(appEventKey);
		try
		{
			billingMapper.minusBilling(billingVo);
			UserVo userVo = userMapper.getUser(userKey);
			userVo.setUserCoin(userVo.getUserCoin() - billingCoin);
			userMapper.updateUser(userVo);
			//여기서 User Coin도 내려줌.
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<BillingVo> getUserBillingList(String userKey) {
		return billingMapper.getUserBillingList(userMapper.getUser(userKey).getUserID());
	}
	@Override
	public List<BillingVo> getAppBillingList(String appKey) {
		return billingMapper.getAppBillingList(appMapper.getAppByKey(appKey).getAppID());
	}
	@Override
	public List<AppEventVo> getAppEventList(int appID) {
		return appEventMapper.getAppEventByAppID(appID);
	}
	@Override
	public boolean registAppEvent(int appID, String appEventContent, int appEventCoin) {
		// TODO Auto-generated method stub
		AppEventVo appEventVo = new AppEventVo();
		appEventVo.setAppID(appID);
		appEventVo.setAppEventContent(appEventContent);
		appEventVo.setAppEventCoin(appEventCoin);
		try
		{
			List<AppEventVo> list = getAppEventList(appID);
			appEventVo.setAppEventKey(list.get(list.size()-1).getAppEventKey()+1);
			appEventMapper.registAppEvent(appEventVo);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public AppEventVo getAppEvent(int appEventID) {
		// TODO Auto-generated method stub
		return appEventMapper.getAppEventByEventID(appEventID);
	}
	public boolean modifyAppEvent(int appEventID, String appEventContent, int appEventCoin, boolean appEventEnable) {
		// TODO Auto-generated method stub
		AppEventVo appEventVo = new AppEventVo();
		appEventVo.setAppEventID(appEventID);
		appEventVo.setAppEventContent(appEventContent);
		appEventVo.setAppEventCoin(appEventCoin);
		appEventVo.setAppEventEnable(appEventEnable);
		try
		{
			appEventMapper.updateAppEvent(appEventVo);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
