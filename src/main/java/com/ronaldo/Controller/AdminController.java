package com.ronaldo.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ronaldo.config.GranConfig;
import com.ronaldo.config.SessionWire;
import com.ronaldo.dao.AppEventDAO;
import com.ronaldo.domain.AppEventVo;
import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.CompanyVo;
import com.ronaldo.domain.ExchangeVo;
import com.ronaldo.domain.UserEventVo;
import com.ronaldo.domain.UserInAppVo;
import com.ronaldo.service.AuthUserServiceImpl;
import com.ronaldo.service.ApiServiceImpl;
@Controller
public class AdminController
{
	@Autowired
	ServletContext context;
	@Autowired
	private AuthUserServiceImpl userService;
	@Autowired
	private ApiServiceImpl apiService;
	@Autowired
	SessionWire sessionWire;
	
	@RequestMapping(value = "/managementapp", method = RequestMethod.GET)
    public String managementapp(Model model){
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementApp(model);
    }
	@RequestMapping(value = "/managementuser", method = RequestMethod.GET)
    public String managementuser(Model model){
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementUser(model);
    }
	@RequestMapping(value = "/managementbilling", method = RequestMethod.GET)
    public String managementbilling(Model model){
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementBilling(model);
    }
	@RequestMapping(value = "/managementexchange", method = RequestMethod.GET)
    public String managementgrancoin(Model model){
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementExchange(model);
    }
	@RequestMapping(value = "/managementcompany", method = RequestMethod.GET)
    public String managementcompany(Model model){
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementCompany(model);
    }
	@RequestMapping(value = "/managementappevent", method = RequestMethod.POST)
    public String managementappevent(Model model,@RequestParam("appID") int appID) {
		if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementAppEvent(model,appID);
    }
	@RequestMapping(value = "/managementuserinfo", method = RequestMethod.POST)
    public String managementuserinfo(Model model,@RequestParam("userID") int userID) {
		if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementUserInfo(model,userID);
    }
	@RequestMapping(value = "/registapp", method = RequestMethod.POST)
    public ResponseEntity<String> registApp(@RequestParam("appPackage") String appPackage
    		, @RequestParam("appURL") String appURL,@RequestParam("companyID") int companyID,
    		@RequestParam("appName") String appName,@RequestParam("appIconImage") MultipartFile appIconImage
    		,@RequestParam("appBannerImage") MultipartFile appBannerImage) {
        try {
        	 // Get the file and save it uploads dir
        	 byte[] iconBytes = appIconImage.getBytes();
        	 String iconOriginalFileName = appIconImage.getOriginalFilename();
             String iconOriginalFileExtension = iconOriginalFileName.substring(iconOriginalFileName.lastIndexOf("."));
             String iconFileName = appName+"_v1_icon"+iconOriginalFileExtension;
             Path iconPath = Paths.get(context.getRealPath("image/appIcon/") + iconFileName);
             
             byte[] bannerBytes = appBannerImage.getBytes();
        	 String bannerOriginalFileName = appBannerImage.getOriginalFilename();
             String bannerOriginalFileExtension = bannerOriginalFileName.substring(bannerOriginalFileName.lastIndexOf("."));
             String bannerFileName = appName+"_v1_banner"+bannerOriginalFileExtension;
             Path bannerPath = Paths.get(context.getRealPath("image/appBanner/") + bannerFileName);
             
             if(apiService.registApp(appName,companyID, appURL, iconFileName,bannerFileName, appPackage))
             {
                 Files.write(iconPath, iconBytes);
                 Files.write(bannerPath, bannerBytes);
            	 return new ResponseEntity<>(GranConfig.RETURN_APP_REGIST_SECCESS,HttpStatus.OK);
             }
             else
             {
            	 return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
             }
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
        }
    }
	@RequestMapping(value = "/modifyapp", method = RequestMethod.POST)
    public ResponseEntity<String> modifyApp(@RequestParam("appID") int appID,@RequestParam("appPackage") String appPackage
    		, @RequestParam("appURL") String appURL,@RequestParam("companyID") int companyID,
    		@RequestParam("appName") String appName,@RequestParam("appIconImage") MultipartFile appIconImage
    		,@RequestParam("appBannerImage") MultipartFile appBannerImage,@RequestParam("appEnable") boolean appEnable) {
		 try {
        	 // Get the file and save it uploads dir
			 AppVo appVo = apiService.getApp(appID);
			 String imageIconPath = appVo.getAppImageIconPath();
			 String imageBannerPath = appVo.getAppImageBannerPath();
			 byte[] iconBytes = appIconImage.getBytes();
			 byte[] bannerBytes = appBannerImage.getBytes();
			 if(iconBytes.length != 0)
			 {
	        	 String iconOriginalFileName = appIconImage.getOriginalFilename();
	             String iconOriginalFileExtension = iconOriginalFileName.substring(iconOriginalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(imageIconPath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 imageIconPath = appName+"_v"+ver+"_icon"+iconOriginalFileExtension;
			 }
			 if(bannerBytes.length != 0)
			 {
				 String bannerOriginalFileName = appBannerImage.getOriginalFilename();
	             String bannerOriginalFileExtension = bannerOriginalFileName.substring(bannerOriginalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(imageBannerPath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 imageBannerPath = appName+"_v"+ver+"_banner"+bannerOriginalFileExtension;
			 }
			if(apiService.modifyApp(appID,appName,companyID, appURL, imageIconPath,imageBannerPath, appPackage,appEnable))
			{
				if(iconBytes.length != 0)
				{
		            Path iconPath = Paths.get(context.getRealPath("image/appIcon/") + imageIconPath);
				    Files.write(iconPath, iconBytes);
				}
				if(bannerBytes.length != 0)
				{
		            Path bannerPath = Paths.get(context.getRealPath("image/appBanner/") + imageBannerPath);
				    Files.write(bannerPath, bannerBytes);
				}
				return new ResponseEntity<>(GranConfig.RETURN_APP_MODIFY_SECCESS,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
			}
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
        }
    }
	@RequestMapping(value = "/getapp", method = RequestMethod.POST)
    public ResponseEntity<AppVo> getApp(@RequestParam("appID") int appID) {
        return new ResponseEntity<>(apiService.getApp(appID),HttpStatus.OK);
    }
	@RequestMapping(value = "/getappevent", method = RequestMethod.POST)
    public ResponseEntity<AppEventVo> getAppEvent(@RequestParam("appEventID") int appEventID) {
        return new ResponseEntity<>(apiService.getAppEvent(appEventID),HttpStatus.OK);
    }
	@RequestMapping(value = "/registappevent", method = RequestMethod.POST)
    public ResponseEntity<String> registappevent(@RequestParam("appID") int appID,
    		@RequestParam("appEventContent") String appEventContent,@RequestParam("appEventKey") String appEventKey
    		, @RequestParam("appEventCoin") int appEventCoin, @RequestParam("appEventStartTime") String appEventStartTime
    		, @RequestParam("appEventEndTime") String appEventEndTime, @RequestParam("appEventLimit") int appEventLimit) {
        if(apiService.registAppEvent(appID,appEventContent,appEventCoin,Timestamp.valueOf(appEventStartTime.replace("T"," ")),
        		Timestamp.valueOf(appEventEndTime.replace("T"," ")),appEventKey,appEventLimit))
        {
            return new ResponseEntity<>(GranConfig.RETURN_APP_REGIST_SECCESS,HttpStatus.OK);
        }
        return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/modifyappevent", method = RequestMethod.POST)
    public ResponseEntity<String> modifyappevent(@RequestParam("appEventID") int appEventID,
    		@RequestParam("appEventContent") String appEventContent,@RequestParam("appEventKey") String appEventKey
    		, @RequestParam("appEventCoin") int appEventCoin,@RequestParam("appEventEnable") boolean appEventEnable,
    		 @RequestParam("appEventStartTime") String appEventStartTime, @RequestParam("appEventEndTime") String appEventEndTime
    		 , @RequestParam("appEventLimit") int appEventLimit) {
		if(apiService.modifyAppEvent(appEventID,appEventContent,appEventCoin,appEventEnable,Timestamp.valueOf(appEventStartTime.replace("T"," ")),
        		Timestamp.valueOf(appEventEndTime.replace("T"," ")),appEventKey,appEventLimit))
		{
			 return new ResponseEntity<>(GranConfig.RETURN_APP_REGIST_SECCESS,HttpStatus.OK);
		}
        return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/registexchange", method = RequestMethod.POST)
    public ResponseEntity<String> registExchange(@RequestParam("exchangeMoney") int exchangeMoney,
    		@RequestParam("exchangeCoin") int exchangeCoin,@RequestParam("exchangeKey") String exchangeKey,
    		@RequestParam("exchangeName") String exchangeName,@RequestParam("exchangeImage") MultipartFile exchangeImage) {
		 try {
        	 // Get the file and save it uploads dir
        	 byte[] bytes = exchangeImage.getBytes();
        	 String originalFileName = exchangeImage.getOriginalFilename();
             String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
             Path path = Paths.get(context.getRealPath("image/exchange/") + exchangeName+"_v1"+originalFileExtension);
         
             if(apiService.registExchange(exchangeMoney,exchangeCoin,exchangeName,exchangeKey,exchangeName+"_v1"+originalFileExtension))
             {
                 Files.write(path, bytes);
            	 return new ResponseEntity<>(GranConfig.RETURN_APP_REGIST_SECCESS,HttpStatus.OK);
             }
             else
             {
            	 return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
             }
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
        }
    }
	@RequestMapping(value = "/modifyexchange", method = RequestMethod.POST)
    public ResponseEntity<String> modifyExchange(@RequestParam("exchangeMoney") int exchangeMoney,
    		@RequestParam("exchangeCoin") int exchangeCoin,@RequestParam("exchangeKey") String exchangeKey,
    		@RequestParam("exchangeName") String exchangeName,@RequestParam("exchangeImage") MultipartFile exchangeImage,
    		@RequestParam("exchangeID") int exchangeID,@RequestParam("exchangeEnable") boolean exchangeEnable) {
        try {
       	 // Get the file and save it uploads dir
			 String ImagePath = apiService.getExchange(exchangeID).getExchangeImagePath();
			 byte[] bytes = exchangeImage.getBytes();
			 if(bytes.length != 0)
			 {
				 String originalFileName = exchangeImage.getOriginalFilename();
	             String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(ImagePath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 ImagePath = exchangeName+"_v"+ver+originalFileExtension;
			 }
			if(apiService.modifyExchange(exchangeID,exchangeMoney,exchangeCoin,exchangeEnable,exchangeName,exchangeKey,ImagePath))
			{
				if(bytes.length != 0)
				{
					Path path = Paths.get(context.getRealPath("image/exchange/") + ImagePath);
				    Files.write(path, bytes);
				}
				return new ResponseEntity<>(GranConfig.RETURN_APP_MODIFY_SECCESS,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
			}
       } catch (Exception e) {
    	   e.printStackTrace();
           return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
       }
    }
	@RequestMapping(value = "/getexchange", method = RequestMethod.POST)
    public ResponseEntity<ExchangeVo> getExchange(@RequestParam("exchangeID") int exchangeID) {
        return new ResponseEntity<>(apiService.getExchange(exchangeID),HttpStatus.OK);
    }
	@RequestMapping(value = "/registcompany", method = RequestMethod.POST)
    public ResponseEntity<String> registCompany(@RequestParam("companyName") String companyName) {
		if(apiService.registCompany(companyName))
		{
			 return new ResponseEntity<>(GranConfig.RETURN_COMPANY_REGIST_SECCESS,HttpStatus.OK);
		}
        return new ResponseEntity<>(GranConfig.RETURN_COMPANY_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/modifycompany", method = RequestMethod.POST)
    public ResponseEntity<String> modifyCompany(@RequestParam("companyName") String companyName,
    		@RequestParam("companyID") int companyID,@RequestParam("companyEnable") boolean companyEnable) {
		if(apiService.modifyCompany(companyID,companyName,companyEnable))
		{
			 return new ResponseEntity<>(GranConfig.RETURN_COMPANY_MODIFY_SECCESS,HttpStatus.OK);
		}
        return new ResponseEntity<>(GranConfig.RETURN_COMPANY_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/getcompany", method = RequestMethod.POST)
    public ResponseEntity<CompanyVo> getCompany(@RequestParam("companyID") int companyID) {
        return new ResponseEntity<>(apiService.getCompany(companyID),HttpStatus.OK);
    }
	
	private String setManagementApp(Model model)
    {
		ArrayList<AppVo> appList = (ArrayList<AppVo>)apiService.getAppList();
		ArrayList<CompanyVo> companyList = (ArrayList<CompanyVo>)apiService.getCompanyList();
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("companylist",companyList);
    	model.addAttribute("applist",appList);
    	return "managementapp";
    }
	private String setManagementCompany(Model model)
    {
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("companylist",apiService.getCompanyList());
    	return "managementcompany";
    }
	private String setManagementAppEvent(Model model,int appID)
    {
		List<AppEventVo> list = apiService.getAppEventList(appID);
		model.addAttribute("app",apiService.getApp(appID));
    	model.addAttribute("eventList",list);
    	return "managementappevent";
    }
	private String setManagementUser(Model model)
    {
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("userlist",apiService.getUserList());
    	return "managementuser";
    }
	private String setManagementUserInfo(Model model,int userID)
    {
		List<UserInAppVo> userInAppList = apiService.getUserInAppByUserID(userID);
		List<UserEventVo> userEventList = apiService.getUserEventList(userID);
		for(int i=0;i<userInAppList.size();i++)
		{
			List<AppEventVo> appEventList = apiService.getAppEventList(userInAppList.get(i).getAppID());
			for(int j=0;j<appEventList.size();j++)
			{
				appEventList.get(j).setAppEventRewardEnable("X");
				appEventList.get(j).setAppEventSuccessEnable("X");
				for(int k=0;k<userEventList.size();k++)
				{
					if(userEventList.get(k).getAppEventID()==appEventList.get(j).getAppEventID())
					{
						appEventList.get(j).setAppEventSuccessEnable("O");
						if(userEventList.get(k).isUserEventEnable())
						{
							appEventList.get(j).setAppEventRewardEnable("O");
						}
						break;
					}
				}
			}
			userInAppList.get(i).setAppEventList(appEventList);
		}
		model.addAttribute("user",apiService.getUser(userID));
		model.addAttribute("userinapplist",userInAppList);
    	return "managementuserinfo";
    }
	private String setManagementBilling(Model model)
    {
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("billinglist",apiService.getBillingList());
    	return "managementbilling";
    }
	private String setManagementExchange(Model model)
    {
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("exchangelist",apiService.getExchangeList());
    	return "managementexchange";
    }
	private String setRedirectLogin(Model model)
    {
    	model.addAttribute("message", "그랑몬스터");
    	return "redirect:/login";
    }
}
