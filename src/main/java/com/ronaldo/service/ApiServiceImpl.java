package com.ronaldo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ronaldo.config.ErrorCodeConfig.AppListEnum;
import com.ronaldo.config.ErrorCodeConfig.EventEnum;
import com.ronaldo.config.ErrorCodeConfig.EventRewardEnum;
import com.ronaldo.config.ErrorCodeConfig.ExchangeEnum;
import com.ronaldo.config.ErrorCodeConfig.ExhaustEnum;
import com.ronaldo.config.ErrorCodeConfig.LoginEnum;
import com.ronaldo.config.ErrorCodeConfig.PayloadEnum;
import com.ronaldo.config.ErrorCodeConfig.PurchaseEnum;

import com.ronaldo.domain.AppEventDTO;
import com.ronaldo.domain.AppDTO;
import com.ronaldo.domain.BillingDTO;
import com.ronaldo.domain.CompanyDTO;
import com.ronaldo.domain.UserInAppDTO;
import com.ronaldo.domain.UserDTO;
import com.ronaldo.domain.ExchangeDTO;
import com.ronaldo.domain.UserEventDTO;

import com.ronaldo.mapper.BillingMapper;
import com.ronaldo.mapper.AppEventMapper;
import com.ronaldo.mapper.AppMapper;
import com.ronaldo.mapper.CompanyMapper;
import com.ronaldo.mapper.ExchangeMapper;
import com.ronaldo.mapper.UserEventMapper;
import com.ronaldo.mapper.UserInAppMapper;
import com.ronaldo.mapper.UserMapper;

import com.ronaldo.vo.ReceiveAppEventVO;
import com.ronaldo.vo.ReceiveAppListVO;
import com.ronaldo.vo.ReceiveAppVO;
import com.ronaldo.vo.ReceiveCompanyVO;
import com.ronaldo.vo.ReceiveEventRewardVO;
import com.ronaldo.vo.ReceiveEventVO;
import com.ronaldo.vo.ReceiveExchangeAPIVO;
import com.ronaldo.vo.ReceiveExchangeVO;
import com.ronaldo.vo.ReceiveExhaustVO;
import com.ronaldo.vo.ReceivePayloadVO;
import com.ronaldo.vo.ReceivePurchaseVO;
import com.ronaldo.vo.ReceiveUserVO;

import com.ronaldo.vo.ReturnAppEventVO;
import com.ronaldo.vo.ReturnAppListVO;
import com.ronaldo.vo.ReturnAppVO;
import com.ronaldo.vo.ReturnEventRewardVO;
import com.ronaldo.vo.ReturnEventVO;
import com.ronaldo.vo.ReturnExchangeListVO;
import com.ronaldo.vo.ReturnExchangeVO;
import com.ronaldo.vo.ReturnExhaustVO;
import com.ronaldo.vo.ReturnPayloadVO;
import com.ronaldo.vo.ReturnPurchaseVO;
import com.ronaldo.vo.ReturnUserVO;

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
	private ExchangeMapper exchangeMapper;
	
	@Autowired
	private BillingMapper billingMapper;
	
	@Autowired
	private AppEventMapper appEventMapper;
	
	@Autowired
	private UserInAppMapper userInAppMapper;

	@Autowired
	private UserEventMapper userEventMapper;

	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private static final Logger LOG = LoggerFactory.getLogger(ApiServiceImpl.class);
	 
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public boolean registApp(ReceiveAppVO receiveAppVO, String appImageIconPath,String appImageHBannerPath,String appImageVBannerPath) {
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setName("registapp");
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
		AppDTO appDTO = new AppDTO();
		appDTO.setAppName(receiveAppVO.getAppName());
		appDTO.setCompanyID(receiveAppVO.getCompanyID());
		appDTO.setAppImageIconPath(appImageIconPath);
		appDTO.setAppImageHBannerPath(appImageHBannerPath);
		appDTO.setAppImageVBannerPath(appImageVBannerPath);
		appDTO.setAppURL(receiveAppVO.getAppURL());
		appDTO.setAppPackage(receiveAppVO.getAppPackage());
		try
		{
			appMapper.registApp(appDTO);
			appDTO.setAppKey(passwordEncoder.encode(String.valueOf(appDTO.getAppID())));
			appMapper.modifyAppKey(appDTO);
			dataSourceTransactionManager.commit(transactionStatus);
			return true;
		}
		catch(Exception e)
		{
			dataSourceTransactionManager.rollback(transactionStatus);
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public boolean modifyApp(ReceiveAppVO receiveAppVO,String appImageIconPath,String appImageHBannerPath,String appImageVBannerPath)
	{
		AppDTO appDTO = new AppDTO();
		appDTO.setAppID(receiveAppVO.getAppID());
		appDTO.setAppName(receiveAppVO.getAppName());
		appDTO.setCompanyID(receiveAppVO.getCompanyID());
		appDTO.setAppImageIconPath(appImageIconPath);
		appDTO.setAppImageHBannerPath(appImageHBannerPath);
		appDTO.setAppImageVBannerPath(appImageVBannerPath);
		appDTO.setAppURL(receiveAppVO.getAppURL());
		appDTO.setAppPackage(receiveAppVO.getAppPackage());
		appDTO.setAppEnable(receiveAppVO.isAppEnable());
		try
		{
			appMapper.updateApp(appDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public AppDTO getApp(int appID) {
		return appMapper.getAppByID(appID);
	}
	@Override
	public AppDTO getApp(String appKey) {
		return appMapper.getAppByKey(appKey);
	}
	
	@Override
	public List<AppDTO> getAppList() {
		return appMapper.getAppList();
	}
	@Override
	public List<AppDTO> getEnableAppList(boolean appEnable) {
		return appMapper.getEnableAppList(appEnable);
	}
	@Override
	public boolean registCompany(ReceiveCompanyVO receiveCompanyVO) {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyName(receiveCompanyVO.getCompanyName());
		try
		{
			companyMapper.registCompany(companyDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public boolean modifyCompany(ReceiveCompanyVO receiveCompanyVO)
	{
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyID(receiveCompanyVO.getCompanyID());
		companyDTO.setCompanyName(receiveCompanyVO.getCompanyName());
		companyDTO.setCompanyEnable(receiveCompanyVO.isCompanyEnable());
		try
		{
			companyMapper.updateCompany(companyDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public CompanyDTO getCompany(int companyID) {
		return companyMapper.getCompany(companyID);
	}
	@Override
	public List<CompanyDTO> getCompanyList() {
		return companyMapper.getCompanyList();
	}

	@Override
	public boolean registUser(String userKey) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserKey(userKey);
		userDTO.setUserEmail("none");
		userDTO.setUserPassword("none");
		userDTO.setUserMoney(0);
		userDTO.setUserCoin(0);
		try
		{
			userMapper.registUser(userDTO); // 회원가입.
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public boolean registUserInApp(int userID,int appID) {
		try
		{
			UserInAppDTO userInAppDTO = new UserInAppDTO();
			userInAppDTO.setUserID(userID);
			userInAppDTO.setAppID(appID);
			//여기서 그 유저가 이 앱에 로그인이 되어 있다면?
			userInAppMapper.registUserInApp(userInAppDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public UserInAppDTO getUserInApp(int userID,int appID) {
		UserInAppDTO userInAppDTO = new UserInAppDTO();
		userInAppDTO.setUserID(userID);
		userInAppDTO.setAppID(appID);
		return userInAppMapper.getUserInApp(userInAppDTO);
	}
	@Override
	public List<UserInAppDTO> getUserInAppByUserID(int userID) {
		return userInAppMapper.getUserInAppByUserID(userID);
	}
	@Override
	public List<UserDTO> getUserList() {
		return userMapper.getUserList();
	}
	@Override
	public UserDTO getUser(String userKey) {
		return userMapper.getUser(userKey);
	}
	@Override
	public UserDTO getUser(int userID) {
		return userMapper.getUserByUserID(userID);
	}
	@Override
	public List<BillingDTO> getBillingList() {
		return billingMapper.getBillingList();
	}
	@Override
	public boolean addBilling(UserDTO userDTO, int appID, int billingCoin, int billingMoney,String billingType) {
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setName("addBilling");
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
		BillingDTO billingDTO = new BillingDTO();
		billingDTO.setUserID(userDTO.getUserID());
		billingDTO.setAppID(appID);
		billingDTO.setBillingCoin(billingCoin);
		billingDTO.setBillingMoney(billingMoney);
		billingDTO.setBillingType(billingType);
		try
		{
			billingMapper.registBilling(billingDTO);
			userDTO.setUserCoin(userDTO.getUserCoin() + billingCoin);
			userDTO.setUserMoney(userDTO.getUserMoney() + billingMoney);
			userDTO.setUserPayload("");
			userMapper.updateUser(userDTO);
			dataSourceTransactionManager.commit(transactionStatus);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			dataSourceTransactionManager.rollback(transactionStatus);
			return false;
		}
	}
	@Override
	public boolean minusBilling(UserDTO userDTO, int appID, int billingCoin,String billingType) {
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setName("minusBilling");
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
		BillingDTO billingDTO = new BillingDTO();
		billingDTO.setUserID(userDTO.getUserID());
		billingDTO.setAppID(appID);
		billingDTO.setBillingCoin(billingCoin);
		billingDTO.setBillingType(billingType);
		try
		{
			billingMapper.registBilling(billingDTO);
			userDTO.setUserCoin(userDTO.getUserCoin() - billingCoin);
			userMapper.updateUser(userDTO);
			dataSourceTransactionManager.commit(transactionStatus);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			dataSourceTransactionManager.rollback(transactionStatus);
			return false;
		}
	}
	@Override
	public List<BillingDTO> getUserBillingList(String userKey) {
		return billingMapper.getUserBillingList(userMapper.getUser(userKey).getUserID());
	}
	@Override
	public List<BillingDTO> getAppBillingList(String appKey) {
		return billingMapper.getAppBillingList(appMapper.getAppByKey(appKey).getAppID());
	}
	@Override
	public List<AppEventDTO> getAppEventList(int appID) {
		return appEventMapper.getAppEventByAppID(appID);
	}
	@Override
	public List<AppEventDTO> getAppEventEnableList(int appID) {
		return appEventMapper.getAppEventByAppIDByEnable(appID);
	}
	@Override
	public boolean registAppEvent(ReceiveAppEventVO receiveAppEventVO) {
		AppEventDTO appEventDTO = new AppEventDTO();
		appEventDTO.setAppID(receiveAppEventVO.getAppID());
		appEventDTO.setAppEventContent(receiveAppEventVO.getAppEventContent());
		appEventDTO.setAppEventCoin(receiveAppEventVO.getAppEventCoin());
		appEventDTO.setAppEventStartTime(Timestamp.valueOf(receiveAppEventVO.getAppEventReservationTime().substring(0,19)));
		appEventDTO.setAppEventEndTime(Timestamp.valueOf(receiveAppEventVO.getAppEventReservationTime().substring(22,41)));
		appEventDTO.setAppEventKey(receiveAppEventVO.getAppEventKey());
		appEventDTO.setAppEventLimit(receiveAppEventVO.getAppEventLimit());
		try
		{
			appEventMapper.registAppEvent(appEventDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public AppEventDTO getAppEvent(int appEventID) {
		return appEventMapper.getAppEventByEventID(appEventID);
	}
	@Override
	public AppEventDTO getAppEvent(int appID,String appEventKey) {
		AppEventDTO appEventDTO = new AppEventDTO();
		appEventDTO.setAppID(appID);
		appEventDTO.setAppEventKey(appEventKey);
		return appEventMapper.getAppEventByAppIDByEventKey(appEventDTO);
	}
	@Override
	public boolean modifyAppEvent(ReceiveAppEventVO receiveAppEventVO) {
		AppEventDTO appEventDTO = new AppEventDTO();
		appEventDTO.setAppEventID(receiveAppEventVO.getAppEventID());
		appEventDTO.setAppEventContent(receiveAppEventVO.getAppEventContent());
		appEventDTO.setAppEventCoin(receiveAppEventVO.getAppEventCoin());
		appEventDTO.setAppEventEnable(receiveAppEventVO.isAppEventEnable());
		appEventDTO.setAppEventStartTime(Timestamp.valueOf(receiveAppEventVO.getAppEventReservationTime().substring(0,19)));
		appEventDTO.setAppEventEndTime(Timestamp.valueOf(receiveAppEventVO.getAppEventReservationTime().substring(22,41)));
		appEventDTO.setAppEventKey(receiveAppEventVO.getAppEventKey());
		appEventDTO.setAppEventLimit(receiveAppEventVO.getAppEventLimit());
		try
		{
			appEventMapper.updateAppEvent(appEventDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public String setUserPayload(String appKey, String userKey) {
		Date date = new Date();
		String encode = appKey+userKey+date.toString();
		String payload = passwordEncoder.encode(encode);
		UserDTO userDTO = new UserDTO();
		userDTO.setUserKey(userKey);
		userDTO.setUserPayload(payload);
		try {
			userMapper.updateUserPayload(userDTO);
		} catch (Exception e) {
			payload = "fail";
			LOG.info(e.getMessage());
		}
		return payload;
	}
	@Override
	public String getUserPayload(String userKey) {
		return userMapper.getUser(userKey).getUserPayload();
	}
	@Override
	public List<ExchangeDTO> getExchangeList() {
		return exchangeMapper.getExchangeList();
	}
	@Override
	public List<ExchangeDTO> getEnableExchangeList(boolean exchangeEnable) {
		return exchangeMapper.getEnableExchangeList(exchangeEnable);
	}
	@Override
	public boolean modifyExchange(ReceiveExchangeVO receiveExchangeVO, String HFileName,String VFileName) {
		ExchangeDTO exchangeDTO = new ExchangeDTO();
		exchangeDTO.setExchangeID(receiveExchangeVO.getExchangeID());
		exchangeDTO.setExchangeMoney(receiveExchangeVO.getExchangeMoney());
		exchangeDTO.setExchangeCoin(receiveExchangeVO.getExchangeCoin());
		exchangeDTO.setExchangeEnable(receiveExchangeVO.isExchangeEnable());
		exchangeDTO.setExchangeName(receiveExchangeVO.getExchangeName());
		exchangeDTO.setExchangeHImagePath(HFileName);
		exchangeDTO.setExchangeVImagePath(VFileName);
		exchangeDTO.setExchangeKey(receiveExchangeVO.getExchangeKey());
		try
		{
			exchangeMapper.updateExchange(exchangeDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public ExchangeDTO getExchange(int exchangeID) {
		return exchangeMapper.getExchange(exchangeID);
	}
	@Override
	public boolean registExchange(ReceiveExchangeVO receiveExchangeVO, String HFileName,String VFileName) {
		ExchangeDTO exchangeDTO = new ExchangeDTO();
		exchangeDTO.setExchangeMoney(receiveExchangeVO.getExchangeMoney());
		exchangeDTO.setExchangeCoin(receiveExchangeVO.getExchangeCoin());
		exchangeDTO.setExchangeName(receiveExchangeVO.getExchangeName());
		exchangeDTO.setExchangeHImagePath(HFileName);
		exchangeDTO.setExchangeVImagePath(VFileName);
		exchangeDTO.setExchangeKey(receiveExchangeVO.getExchangeKey());
		try
		{
			exchangeMapper.registExchange(exchangeDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}

	@Override
	public UserEventDTO getUserEvent(int userID, int appEventID) {
		UserEventDTO userEventDTO = new UserEventDTO();
		userEventDTO.setUserID(userID);
		userEventDTO.setAppEventID(appEventID);
		return userEventMapper.getUserEvent(userEventDTO);
	}
	@Override
	public boolean registUserEvent(int userID, int appEventID) {
		UserEventDTO userEventDTO = new UserEventDTO();
		userEventDTO.setUserID(userID);
		userEventDTO.setAppEventID(appEventID);
		try
		{
			userEventMapper.registUserEvent(userEventDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public boolean modifyUserEvent(int userEventID,int userID, int appEventID) {
		UserEventDTO userEventDTO = new UserEventDTO();
		userEventDTO.setUserEventID(userEventID);
		userEventDTO.setUserID(userID);
		userEventDTO.setAppEventID(appEventID);
		userEventDTO.setUserEventEnable(true);
		try
		{
			userEventMapper.updateUserEvent(userEventDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public List<UserEventDTO> getUserEventList(int userID) {
		return userEventMapper.getUserEventList(userID);
	}
	@Override
	public void login(ReceiveUserVO receiveUserVO, ReturnUserVO returnUserVO) 
	{
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setName("login");
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
		String appKey = receiveUserVO.getAppKey();
		String userKey = receiveUserVO.getUserKey();
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null)
		{
			returnUserVO.setState(LoginEnum.NOT_EXIST_APPKEY);
			LOG.info("login(NOT_EXIST_APPKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null) // 모든 앱에서 처음 가입이면.
		{
			if (!registUser(userKey)) // 일단 유저 등록시키고.
			{
				returnUserVO.setState(LoginEnum.USER_KEY_INVALID);
				LOG.info("login(USER_KEY_INVALID) - AppKey : " + appKey+" / UserKey : "+userKey);
				return;
			}
			userDTO = getUser(userKey);
		} 
		if (getUserInApp(userDTO.getUserID() , appDTO.getAppID()) == null) // 앱 처음이면 앱 등록시킨다.
		{
			if(!registUserInApp(userDTO.getUserID() , appDTO.getAppID()))
			{
				returnUserVO.setState(LoginEnum.USER_ALREADY_JOIN_APP);
				LOG.info("login(USER_ALREADY_JOIN_APP) - AppKey : " + appKey+" / UserKey : "+userKey);
				dataSourceTransactionManager.rollback(transactionStatus);
				return;
			}
		}
		dataSourceTransactionManager.commit(transactionStatus);
		returnUserVO.setUserCoin(userDTO.getUserCoin());
		returnUserVO.setUserEmail(userDTO.getUserEmail());
		returnUserVO.setState(LoginEnum.SUCCESS);
		return;
	}
	@Override
	public void appList(ReceiveAppListVO receiveAppListVO, ReturnAppListVO returnAppListVO)
	{
		String appKey = receiveAppListVO.getAppKey();
		String userKey = receiveAppListVO.getUserKey();
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null)
		{
			returnAppListVO.setState(AppListEnum.NOT_EXIST_APPKEY);
			LOG.info("appList(NOT_EXIST_APPKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		List<ReturnAppVO> returnAppVoList = new ArrayList<ReturnAppVO>();
		List<AppDTO> appDTOList = getEnableAppList(true);
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null)
		{
			returnAppListVO.setState(AppListEnum.NOT_EXIST_USERKEY);
			LOG.info("appList(NOT_EXIST_USERKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		List<UserInAppDTO> userInAppList = getUserInAppByUserID(userDTO.getUserID());
		List<UserEventDTO> userEventList = getUserEventList(userDTO.getUserID());
		
		for (int i = 0; i < appDTOList.size(); i++) 
		{
			ReturnAppVO returnAppVO = new ReturnAppVO();
			returnAppVO.setAppKey(appDTOList.get(i).getAppKey());
			returnAppVO.setAppImageIconPath(appDTOList.get(i).getAppImageIconPath());
			returnAppVO.setAppImageHBannerPath(appDTOList.get(i).getAppImageHBannerPath());
			returnAppVO.setAppImageVBannerPath(appDTOList.get(i).getAppImageVBannerPath());
			returnAppVO.setAppName(appDTOList.get(i).getAppName());
			returnAppVO.setAppPackage(appDTOList.get(i).getAppPackage());
			returnAppVO.setAppURL(appDTOList.get(i).getAppURL());
			returnAppVO.setAppInstall(false);
			// event 확인하기 (userKey로)
			List<AppEventDTO> appEventList = getAppEventEnableList(appDTOList.get(i).getAppID());
			
			List<ReturnAppEventVO> returnEventList = new ArrayList<ReturnAppEventVO>();
			for(int j=0;j<appEventList.size();j++)
			{
				if(appEventList.get(j).getAppEventEndTime().getTime() < System.currentTimeMillis())// 시간지났으면
				{
					disableAppEvent(appEventList.get(j).getAppEventID());
					LOG.info("appList(ALREADY_EVENT_END) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventList.get(j).getAppEventKey());
					continue;
				}
				if(appEventList.get(j).getAppEventStartTime().getTime() > System.currentTimeMillis())// 시작시간이 지금보다 앞서면
				{
					LOG.info("appList(NOT_YET_EVENT_START) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventList.get(j).getAppEventKey());
					continue;
				}
				ReturnAppEventVO appEventVO = new ReturnAppEventVO();
				appEventVO.setAppEventKey(appEventList.get(j).getAppEventKey());
				appEventVO.setAppEventContent(appEventList.get(j).getAppEventContent());
				appEventVO.setAppEventCoin(appEventList.get(j).getAppEventCoin());
				appEventVO.setAppEventRewardEnable(false);
				appEventVO.setAppEventSuccessEnable(false);
				for(int k=0;k<userEventList.size();k++)
				{
					if(userEventList.get(k).getAppEventID()==appEventList.get(j).getAppEventID())
					{
						appEventVO.setAppEventSuccessEnable(true);
						if(userEventList.get(k).isUserEventEnable())
						{
							appEventVO.setAppEventRewardEnable(true);
						}
						break;
					}
				}
				
				returnEventList.add(appEventVO);
			}
			returnAppVO.setAppEventList(returnEventList);
			for(int j=0;j<userInAppList.size();j++)
			{
				if(userInAppList.get(j).getAppID()==appDTOList.get(i).getAppID())
				{
					returnAppVO.setAppInstall(true); // 첫고객 판단
					// 설치 여부는 패키지로 판단.
				}
			}
			returnAppVoList.add(returnAppVO);
		}
		returnAppListVO.setState(AppListEnum.SUCCESS);
		returnAppListVO.setAppList(returnAppVoList);
		return;
	}
	@Override
	public void payload(ReceivePayloadVO receivePayloadVO, ReturnPayloadVO returnPayloadVO)
	{
		String appKey = receivePayloadVO.getAppKey();
		String userKey = receivePayloadVO.getUserKey();
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null)
		{
			returnPayloadVO.setState(PayloadEnum.NOT_EXIST_APPKEY);
			LOG.info("payload(NOT_EXIST_APPKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null)
		{
			returnPayloadVO.setState(PayloadEnum.NOT_EXIST_USERKEY);
			LOG.info("payload(NOT_EXIST_USERKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		String payload = setUserPayload(appKey, userKey);
		if(payload.equals("fail"))
		{
			returnPayloadVO.setState(PayloadEnum.USER_KEY_INVALID);
			LOG.info("payload(USER_KEY_INVALID) - AppKey : " + appKey+" / UserKey : "+userKey);
		}
		else
		{
			returnPayloadVO.setState(PayloadEnum.SUCCESS);
		}
		returnPayloadVO.setUserPayload(payload);
		return;
	}
	@Override
	public void exchange(ReceiveExchangeAPIVO receiveExchangeAPIVO, ReturnExchangeListVO exchangeListDAO)
	{
		String appKey = receiveExchangeAPIVO.getAppKey();
		String userKey = receiveExchangeAPIVO.getUserKey();
		List<ExchangeDTO> exchangeList = null;
		List<ReturnExchangeVO> returnExchangeVOList = null;
			
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null)
		{
			exchangeListDAO.setState(ExchangeEnum.NOT_EXIST_APPKEY);
			LOG.info("exchange(NOT_EXIST_APPKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null)
		{
			exchangeListDAO.setState(ExchangeEnum.NOT_EXIST_USERKEY);
			LOG.info("exchange(NOT_EXIST_USERKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}	
		exchangeList = getEnableExchangeList(true);
		returnExchangeVOList = new ArrayList<ReturnExchangeVO>();
			
		for (int i = 0; i < exchangeList.size(); i++) {
			ReturnExchangeVO returnExchangeVO = new ReturnExchangeVO();
			returnExchangeVO.setExchangeCoin(exchangeList.get(i).getExchangeCoin());
			returnExchangeVO.setExchangeMoney(exchangeList.get(i).getExchangeMoney());
			returnExchangeVO.setExchangeName(exchangeList.get(i).getExchangeName());
			returnExchangeVO.setExchangeHImagePath(exchangeList.get(i).getExchangeHImagePath());
			returnExchangeVO.setExchangeVImagePath(exchangeList.get(i).getExchangeVImagePath());
			returnExchangeVO.setExchangeKey(exchangeList.get(i).getExchangeKey());
			returnExchangeVOList.add(returnExchangeVO);
		}
		
		exchangeListDAO.setExchangeList(returnExchangeVOList);
		exchangeListDAO.setState(ExchangeEnum.SUCCESS);
		return;
	}
	@Override
	public void purchase(ReceivePurchaseVO receivePurchaseVO, ReturnPurchaseVO returnPurchaseVO) 
	{
		String appKey = receivePurchaseVO.getAppKey();
		String userKey = receivePurchaseVO.getUserKey();
		String payload = receivePurchaseVO.getPayload();
		int coin = receivePurchaseVO.getCoin();
		int price = receivePurchaseVO.getPrice();
		
		String userpayload = getUserPayload(userKey);
		
		if (!(userpayload.equals(payload))) {
			returnPurchaseVO.setState(PurchaseEnum.NOT_EQUAL_PAYLOAD);
			LOG.info("purchase(NOT_EQUAL_PAYLOAD) - AppKey : " + appKey+" / UserKey : "+userKey+" /rec : "+payload+" /saved : "+userpayload);
			return;
		}
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null) {
			returnPurchaseVO.setState(PurchaseEnum.NOT_EXIST_APPKEY);
			LOG.info("purchase(NOT_EXIST_APPKEY) - " + appKey+"/"+userKey);
			return;
		}
	/*	String emailAddress = "gran-server-service@granmonster-185912.iam.gserviceaccount.com";

		JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
				.setJsonFactory(JSON_FACTORY).setServiceAccountId(emailAddress)
				.setServiceAccountPrivateKeyFromP12File(new File(context.getRealPath("p12/granmonster-160fa837dc4c.p12")))
				.setServiceAccountScopes(Collections.singleton("https://www.googleapis.com/auth/androidpublisher"))
				.build();

		String packageName = appVo.getAppPackage();
		
		AndroidPublisher publisher = new AndroidPublisher.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(packageName).build();
		
		AndroidPublisher.Purchases.Products.Get get = publisher.purchases().products().get(packageName, productId,
				purchaseToken);
		ProductPurchase productPurchase = get.execute();
		System.out.println(productPurchase.toPrettyString());

		// 인앱 상품의 소비 상태. 0 아직 소비 안됨(Yet to be consumed) / 1 소비됨(Consumed)
		Integer consumptionState = productPurchase.getConsumptionState();
		System.out.println(consumptionState);
		// 개발자가 지정한 임의 문자열 정보
		String developerPayload = productPurchase.getDeveloperPayload();
		System.out.println(developerPayload);
		// 구매 상태. 0 구매완료 / 1 취소됨
		Integer purchaseState = productPurchase.getPurchaseState();
		System.out.println(purchaseState);
		// 상품이 구매된 시각. 타임스탬프 형태
		Long purchaseTimeMillis = productPurchase.getPurchaseTimeMillis();
		System.out.println(purchaseTimeMillis);*/
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null)
		{
			returnPurchaseVO.setState(PurchaseEnum.NOT_EXIST_USERKEY);
			LOG.info("purchase(NOT_EXIST_USERKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}	
		if(!addBilling(userDTO, appDTO.getAppID(), coin, price, "purchase"))
		{
			returnPurchaseVO.setState(PurchaseEnum.INVALID_BILLING);
			LOG.info("purchase(INVALID_BILLING) - AppKey : " + appKey+" / UserKey : "+userKey);
		}
		returnPurchaseVO.setState(PurchaseEnum.SUCCESS);
		returnPurchaseVO.setCoin(userDTO.getUserCoin());
		return;
	}
	
	
	@Override
	public void exhaust(ReceiveExhaustVO receiveExhaustVO, ReturnExhaustVO returnExhaustVO) {
		String appKey = receiveExhaustVO.getAppKey();
		String userKey = receiveExhaustVO.getUserKey();
		String payload = receiveExhaustVO.getPayload();
		int coin = receiveExhaustVO.getCoin();
		
		String userpayload = getUserPayload(userKey);
		
		if (!(userpayload.equals(payload))) {
			returnExhaustVO.setState(ExhaustEnum.NOT_EQUAL_PAYLOAD);
			LOG.info("exhaust(NOT_EQUAL_PAYLOAD) - AppKey : " + appKey+" / UserKey : "+userKey+" /rec : "+payload+" /saved : "+userpayload);
			return;
		}
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null) {
			returnExhaustVO.setState(ExhaustEnum.NOT_EXIST_APPKEY);
			LOG.info("exhaust(NOT_EXIST_APPKEY) - " + appKey+"/"+userKey);
			return;
		}
		
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null)
		{
			returnExhaustVO.setState(ExhaustEnum.NOT_EXIST_USERKEY);
			LOG.info("exhaust(NOT_EXIST_USERKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}	
		if(userDTO.getUserCoin() < coin) // 코인이 더 적으면 false 리턴
		{
			returnExhaustVO.setState(ExhaustEnum.NOT_ENOUGH_COIN);
			LOG.info("exhaust(NOT_ENOUGH_COIN) - AppKey : " + appKey+" / UserKey : "+userKey+" /rec : "+coin+" /saved : "+userDTO.getUserCoin());
			return;
		}
		
		if(!minusBilling(userDTO, appDTO.getAppID(), coin, "exhaust"))
		{
			returnExhaustVO.setState(ExhaustEnum.INVALID_BILLING);
			LOG.info("exhaust(INVALID_BILLING) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		returnExhaustVO.setState(ExhaustEnum.SUCCESS);
		returnExhaustVO.setCoin(userDTO.getUserCoin());
		return;
	}
	
	@Override
	public void event(ReceiveEventVO receiveEventVO, ReturnEventVO returnEventVO) {
		String appKey = receiveEventVO.getAppKey();
		String userKey = receiveEventVO.getUserKey();
		String appEventKey = receiveEventVO.getEventKey();
		
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null) {
			returnEventVO.setState(EventEnum.NOT_EXIST_APPKEY);
			LOG.info("event(NOT_EXIST_APPKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		AppEventDTO appEventDTO  = getAppEvent(appDTO.getAppID(),appEventKey);
		if (appEventDTO == null) {
			returnEventVO.setState(EventEnum.NOT_REGIST_EVENT); // 이벤트가 없을때.
			LOG.info("event(NOT_REGIST_EVENT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		
		if(appEventDTO.getAppEventEndTime().getTime() < System.currentTimeMillis())// 시간지났으면
		{
			disableAppEvent(appEventDTO.getAppEventID());
			returnEventVO.setState(EventEnum.ALREADY_EVENT_END); // 이벤트 종료.
			LOG.info("event(ALREADY_EVENT_END) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		
		if(!appEventDTO.isAppEventEnable())
		{
			returnEventVO.setState(EventEnum.NOT_ENABLE_EVENT); // 이벤트가 활성화되지 않았을때
			LOG.info("event(NOT_ENABLE_EVENT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		appEventDTO.getAppEventStartTime();
		appEventDTO.getAppEventEndTime();
		appEventDTO.getAppEventLimit();
		
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null)
		{
			returnEventVO.setState(EventEnum.NOT_EXIST_USERKEY);
			LOG.info("event(NOT_EXIST_USERKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}	

		UserEventDTO userEventDTO = getUserEvent(userDTO.getUserID(),appEventDTO.getAppEventID());
		if(userEventDTO!=null)
		{
			returnEventVO.setState(EventEnum.ALREADY_SUCCESS_EVENT); //  이미 이벤트 진행
			LOG.info("event(ALREADY_SUCCESS_EVENT) AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		if(!registUserEvent(userDTO.getUserID(),appEventDTO.getAppEventID())) // userevent 등록
		{
			returnEventVO.setState(EventEnum.INVALID_EVENT); //등록실패
			LOG.info("event(INVALID_EVENT) AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		returnEventVO.setState(EventEnum.SUCCESS); //성공
		return;
	}
	//Sync...?
	@Override
	public synchronized void eventReward(ReceiveEventRewardVO receiveEventRewardVO, ReturnEventRewardVO returnEventRewardVO) {
		String appKey = receiveEventRewardVO.getAppKey();
		String userKey = receiveEventRewardVO.getUserKey();
		String appEventKey = receiveEventRewardVO.getEventKey();
		
		AppDTO appDTO = getApp(appKey);
		if (appDTO == null) {
			returnEventRewardVO.setState(EventRewardEnum.NOT_EXIST_APPKEY);
			LOG.info("eventReward(NOT_EXIST_APPKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
			
		AppEventDTO appEventDTO  = getAppEvent(appDTO.getAppID(),appEventKey);
		if (appEventDTO == null) {
			returnEventRewardVO.setState(EventRewardEnum.NOT_EXIST_EVENT); // 이벤트가 없을때.
			LOG.info("eventReward(NOT_EXIST_EVENT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		
		if(appEventDTO.getAppEventEndTime().getTime() < System.currentTimeMillis())// 시간지났으면
		{
			disableAppEvent(appEventDTO.getAppEventID());
			returnEventRewardVO.setState(EventRewardEnum.ALREADY_EVENT_END); // 이벤트 종료
			LOG.info("eventReward(ALREADY_EVENT_END) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		if(appEventDTO.getAppEventCount() >= appEventDTO.getAppEventLimit())
		{
			// 여기서 앱이 제한인원 이면 그냥 바로 disable 해버리고 리턴.
			disableAppEvent(appEventDTO.getAppEventID());
			returnEventRewardVO.setState(EventRewardEnum.ALREADY_EVENT_LIMIT_COUNT); // 제한인원 걸림.
			LOG.info("eventReward(ALREADY_EVENT_LIMIT_COUNT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		
		if(!appEventDTO.isAppEventEnable())
		{
			returnEventRewardVO.setState(EventRewardEnum.NOT_ENABLE_EVENT); // 이벤트가 활성화되지 않았을때
			LOG.info("eventReward(NOT_ENABLE_EVENT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		
		UserDTO userDTO = getUser(userKey);
		if (userDTO == null)
		{
			returnEventRewardVO.setState(EventRewardEnum.NOT_EXIST_USERKEY);
			LOG.info("eventReward(NOT_EXIST_USERKEY) - AppKey : " + appKey+" / UserKey : "+userKey);
			return;
		}
		
		UserEventDTO userEventDTO = getUserEvent(userDTO.getUserID(),appEventDTO.getAppEventID());
		if(userEventDTO==null)
		{
			returnEventRewardVO.setState(EventRewardEnum.NOT_ACHIEVE_EVENT); // 이벤트를 달성하지 않았을때
			LOG.info("eventReward(NOT_ACHIEVE_EVENT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		else if(userEventDTO.isUserEventEnable())
		{
			returnEventRewardVO.setState(EventRewardEnum.ALREADY_REWARD_EVENT); // 이미 이벤트 보상을 받았을때
			LOG.info("eventReward(ALREADY_REWARD_EVENT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setName("eventReward");
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
		if(!modifyUserEvent(userEventDTO.getUserEventID(),userDTO.getUserID(),appEventDTO.getAppEventID()))
		{
			returnEventRewardVO.setState(EventRewardEnum.INVALID_USER); // 유저 정보 에러
			LOG.info("eventReward(INVALID_USER) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		if(!addBilling(userDTO, appDTO.getAppID(), appEventDTO.getAppEventCoin(), 0, appEventDTO.getAppEventContent()))
		{
			dataSourceTransactionManager.rollback(transactionStatus);
			returnEventRewardVO.setState(EventRewardEnum.INVALID_BILLING); // 영수증 에러
			LOG.info("eventReward(INVALID_BILLING) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		if(!modifyEventCount(appEventDTO.getAppEventID(),appEventDTO.getAppEventCount()+1))
		{
			dataSourceTransactionManager.rollback(transactionStatus);
			returnEventRewardVO.setState(EventRewardEnum.INVALID_EVENT_COUNT); // 이벤트 카운트 에러
			LOG.info("eventReward(INVALID_EVENT_COUNT) - AppKey : " + appKey+" / UserKey : "+userKey +" /eventKey : "+appEventKey);
			return;
		}
		if(appEventDTO.getAppEventCount()+1 >= appEventDTO.getAppEventLimit() && appEventDTO.getAppEventLimit() != 0)
		{
			disableAppEvent(appEventDTO.getAppEventID());
		}
		dataSourceTransactionManager.commit(transactionStatus);
		
		returnEventRewardVO.setCoin(userDTO.getUserCoin());
		returnEventRewardVO.setState(EventRewardEnum.SUCCESS); // ok
		return;
	}
	
	private boolean modifyEventCount(int appEventID, int appEventCount) {
		AppEventDTO appEventDTO = new AppEventDTO();
		appEventDTO.setAppEventID(appEventID);
		appEventDTO.setAppEventCount(appEventCount);
		try
		{
			appEventMapper.modifyEventCount(appEventDTO);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
	@Override
	public boolean disableAppEvent(int appEventID) {
		try
		{
			appEventMapper.disableAppEvent(appEventID);
			return true;
		}
		catch(Exception e)
		{
			LOG.info(e.getMessage());
			return false;
		}
	}
}
