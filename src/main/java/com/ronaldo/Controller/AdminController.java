package com.ronaldo.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ronaldo.config.GranConfig;
import com.ronaldo.config.SessionWire;
import com.ronaldo.domain.AppEventDTO;
import com.ronaldo.domain.AppDTO;
import com.ronaldo.domain.CompanyDTO;
import com.ronaldo.domain.ExchangeDTO;
import com.ronaldo.domain.UserEventDTO;
import com.ronaldo.domain.UserInAppDTO;
import com.ronaldo.service.ApiService;
import com.ronaldo.service.AuthUserService;
import com.ronaldo.vo.ReceiveAppEventVO;
import com.ronaldo.vo.ReceiveAppVO;
import com.ronaldo.vo.ReceiveCompanyVO;
import com.ronaldo.vo.ReceiveExchangeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AdminController
{
	@Autowired
	private ServletContext context;
	@Autowired
	private AuthUserService userService;
	@Autowired
	private ApiService apiService;
	@Autowired
	private SessionWire sessionWire;
	
	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	 
	@RequestMapping(value = "/managementapp", method = RequestMethod.GET)
    public String managementapp(Model model)
	{
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementApp(model);
    }
	@RequestMapping(value = "/managementsdk", method = RequestMethod.GET)
    public String managementsdk(Model model)
	{
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementSDK(model);
    }
	@RequestMapping(value = "/managementuser", method = RequestMethod.GET)
    public String managementuser(Model model)
	{
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementUser(model);
    }
	@RequestMapping(value = "/managementbilling", method = RequestMethod.GET)
    public String managementbilling(Model model)
	{
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	
    	return setManagementBilling(model);
    }
	@RequestMapping(value = "/managementexchange", method = RequestMethod.GET)
    public String managementgrancoin(Model model)
	{
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementExchange(model);
    }
	@RequestMapping(value = "/managementcompany", method = RequestMethod.GET)
    public String managementcompany(Model model)
	{
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementCompany(model);
    }
	@RequestMapping(value = "/managementappevent", method = RequestMethod.POST)
    public String managementappevent(Model model,@RequestParam("appID") int appID) 
	{
		if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementAppEvent(model,appID);
    }
	@RequestMapping(value = "/managementuserinfo", method = RequestMethod.POST)
    public String managementuserinfo(Model model,@RequestParam("userID") int userID)
	{
		if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementUserInfo(model,userID);
    }
	@RequestMapping(value = "/registapp", method = RequestMethod.POST)
    public ResponseEntity<String> registApp(@ModelAttribute ReceiveAppVO receiveAppVO)
	{
        try {
        	// Need S3 File Server ... 
        	 // Get the file and save it uploads dir
        	 byte[] iconBytes = receiveAppVO.getAppIconImage().getBytes();
        	 String iconOriginalFileName = receiveAppVO.getAppIconImage().getOriginalFilename();
             String iconOriginalFileExtension = iconOriginalFileName.substring(iconOriginalFileName.lastIndexOf("."));
             String iconFileName = receiveAppVO.getAppName()+"_v1_icon"+iconOriginalFileExtension;
             Path iconPath = Paths.get(context.getRealPath("image/appIcon/") + iconFileName);
             
             byte[] HbannerBytes = receiveAppVO.getAppHBannerImage().getBytes();
        	 String HbannerOriginalFileName = receiveAppVO.getAppHBannerImage().getOriginalFilename();
             String HbannerOriginalFileExtension = HbannerOriginalFileName.substring(HbannerOriginalFileName.lastIndexOf("."));
             String HbannerFileName = receiveAppVO.getAppName()+"_v1_Hbanner"+HbannerOriginalFileExtension;
             Path HbannerPath = Paths.get(context.getRealPath("image/appHBanner/") + HbannerFileName);
             
             byte[] VbannerBytes = receiveAppVO.getAppVBannerImage().getBytes();
        	 String VbannerOriginalFileName = receiveAppVO.getAppVBannerImage().getOriginalFilename();
             String VbannerOriginalFileExtension = VbannerOriginalFileName.substring(VbannerOriginalFileName.lastIndexOf("."));
             String VbannerFileName = receiveAppVO.getAppName()+"_v1_Vbanner"+VbannerOriginalFileExtension;
             Path VbannerPath = Paths.get(context.getRealPath("image/appVBanner/") + VbannerFileName);
             
             if(apiService.registApp(receiveAppVO,iconFileName,HbannerFileName,VbannerFileName))
             {
                 Files.write(iconPath, iconBytes);
                 Files.write(HbannerPath, HbannerBytes);
                 Files.write(VbannerPath, VbannerBytes);
            	 return new ResponseEntity<>(GranConfig.RETURN_APP_REGIST_SECCESS,HttpStatus.OK);
             }
             else
             {
            	 return new ResponseEntity<>(GranConfig.RETURN_APP_REGIST_FAIL,HttpStatus.BAD_REQUEST);
             }
        } catch (Exception e) {
        	LOG.info(e.getMessage());
            return new ResponseEntity<>(GranConfig.RETURN_IMAGE_FAIL,HttpStatus.BAD_REQUEST);
        }
    }
	@RequestMapping(value = "/modifyapp", method = RequestMethod.POST)
    public ResponseEntity<String> modifyApp(@ModelAttribute ReceiveAppVO receiveAppVO)
	{
        try {
        	// Need S3 File Server ... 
        	 // Get the file and save it uploads dir
			 AppDTO appDTO = apiService.getApp(receiveAppVO.getAppID());
			 String imageIconPath = appDTO.getAppImageIconPath();
			 String imageHBannerPath = appDTO.getAppImageHBannerPath();
			 String imageVBannerPath = appDTO.getAppImageVBannerPath();
			 byte[] iconBytes = receiveAppVO.getAppIconImage().getBytes();
			 byte[] HbannerBytes = receiveAppVO.getAppHBannerImage().getBytes();
			 byte[] VbannerBytes = receiveAppVO.getAppVBannerImage().getBytes();
			 if(iconBytes.length != 0)
			 {
	        	 String iconOriginalFileName = receiveAppVO.getAppIconImage().getOriginalFilename();
	             String iconOriginalFileExtension = iconOriginalFileName.substring(iconOriginalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(imageIconPath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 imageIconPath = receiveAppVO.getAppName()+"_v"+ver+"_icon"+iconOriginalFileExtension;
			 }
			 if(HbannerBytes.length != 0)
			 {
				 String HbannerOriginalFileName = receiveAppVO.getAppHBannerImage().getOriginalFilename();
	             String HbannerOriginalFileExtension = HbannerOriginalFileName.substring(HbannerOriginalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(imageHBannerPath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 imageHBannerPath = receiveAppVO.getAppName()+"_v"+ver+"_Hbanner"+HbannerOriginalFileExtension;
			 }
			 if(VbannerBytes.length != 0)
			 {
				 String VbannerOriginalFileName = receiveAppVO.getAppVBannerImage().getOriginalFilename();
	             String VbannerOriginalFileExtension = VbannerOriginalFileName.substring(VbannerOriginalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(imageVBannerPath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 imageVBannerPath = receiveAppVO.getAppName()+"_v"+ver+"_Vbanner"+VbannerOriginalFileExtension;
			 }
			if(apiService.modifyApp(receiveAppVO, imageIconPath,imageHBannerPath,imageVBannerPath))
			{
				if(iconBytes.length != 0)
				{
		            Path iconPath = Paths.get(context.getRealPath("image/appIcon/") + imageIconPath);
				    Files.write(iconPath, iconBytes);
				}
				if(HbannerBytes.length != 0)
				{
		            Path HbannerPath = Paths.get(context.getRealPath("image/appHBanner/") + imageHBannerPath);
				    Files.write(HbannerPath, HbannerBytes);
				}
				if(VbannerBytes.length != 0)
				{
		            Path VbannerPath = Paths.get(context.getRealPath("image/appVBanner/") + imageVBannerPath);
				    Files.write(VbannerPath, VbannerBytes);
				}
				return new ResponseEntity<>(GranConfig.RETURN_APP_MODIFY_SECCESS,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(GranConfig.RETURN_APP_MODIFY_FAIL,HttpStatus.BAD_REQUEST);
			}
        } catch (Exception e) {
        	LOG.info(e.getMessage());
            return new ResponseEntity<>(GranConfig.RETURN_IMAGE_FAIL,HttpStatus.BAD_REQUEST);
        }
    }
	@RequestMapping(value = "/getapp", method = RequestMethod.POST)
    public ResponseEntity<AppDTO> getApp(@RequestParam("appID") int appID)
	{
        return new ResponseEntity<>(apiService.getApp(appID),HttpStatus.OK);
    }
	@RequestMapping(value = "/getappevent", method = RequestMethod.POST)
    public ResponseEntity<AppEventDTO> getAppEvent(@RequestParam("appEventID") int appEventID)
	{
		AppEventDTO appEventDTO = apiService.getAppEvent(appEventID);
        return new ResponseEntity<>(appEventDTO,HttpStatus.OK);
    }
	@RequestMapping(value = "/registappevent", method = RequestMethod.POST)
    public ResponseEntity<String> registappevent(@ModelAttribute ReceiveAppEventVO receiveAppEventVO)
	{
        if(apiService.registAppEvent(receiveAppEventVO))
        {
           return new ResponseEntity<>(GranConfig.RETURN_APP_EVENT_REGIST_SECCESS,HttpStatus.OK);
        }
        return new ResponseEntity<>(GranConfig.RETURN_APP_EVENT_REGIST_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/modifyappevent", method = RequestMethod.POST)
    public ResponseEntity<String> modifyappevent(@ModelAttribute ReceiveAppEventVO receiveAppEventVO) 
	{
		if(apiService.modifyAppEvent(receiveAppEventVO))
		{
			 return new ResponseEntity<>(GranConfig.RETURN_APP_EVENT_MODIFY_SECCESS,HttpStatus.OK);
		}
        return new ResponseEntity<>(GranConfig.RETURN_APP_EVENT_MODIFY_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/registexchange", method = RequestMethod.POST)
    public ResponseEntity<String> registExchange(@ModelAttribute ReceiveExchangeVO receiveExchangeVO) 
	{
		// Need S3 File Server ... 
		 try {
             byte[] HBytes = receiveExchangeVO.getExchangeHImage().getBytes();
        	 String HOriginalFileName = receiveExchangeVO.getExchangeHImage().getOriginalFilename();
             String HOriginalFileExtension = HOriginalFileName.substring(HOriginalFileName.lastIndexOf("."));
             String HFileName = receiveExchangeVO.getExchangeName()+"_v1_H"+HOriginalFileExtension;
             Path HExchangePath = Paths.get(context.getRealPath("image/HExchange/") + HFileName);
             
             byte[] VBytes = receiveExchangeVO.getExchangeVImage().getBytes();
        	 String VOriginalFileName = receiveExchangeVO.getExchangeVImage().getOriginalFilename();
             String VOriginalFileExtension = VOriginalFileName.substring(VOriginalFileName.lastIndexOf("."));
             String VFileName = receiveExchangeVO.getExchangeName()+"_v1_V"+VOriginalFileExtension;
             Path VExchangePath = Paths.get(context.getRealPath("image/VExchange/") + VFileName);
             
             if(apiService.registExchange(receiveExchangeVO,HFileName,VFileName))
             {
                 Files.write(HExchangePath, HBytes);
                 Files.write(VExchangePath, VBytes);
            	 return new ResponseEntity<>(GranConfig.RETURN_EXCHANGE_REGIST_SECCESS,HttpStatus.OK);
             }
             else
             {
            	 return new ResponseEntity<>(GranConfig.RETURN_EXCHANGE_REGIST_FAIL,HttpStatus.BAD_REQUEST);
             }
        } catch (Exception e) {
        	LOG.info(e.getMessage());
            return new ResponseEntity<>(GranConfig.RETURN_IMAGE_FAIL,HttpStatus.BAD_REQUEST);
        }
    }
	@RequestMapping(value = "/modifyexchange", method = RequestMethod.POST)
    public ResponseEntity<String> modifyExchange(@ModelAttribute ReceiveExchangeVO receiveExchangeVO)
	{
		// Need S3 File Server ... 
        try {
       	 // Get the file and save it uploads dir
        	ExchangeDTO exchangeDTO = apiService.getExchange(receiveExchangeVO.getExchangeID());
			 String HImagePath = exchangeDTO.getExchangeHImagePath();
			 String VImagePath = exchangeDTO.getExchangeVImagePath();
			 byte[] Hbytes = receiveExchangeVO.getExchangeHImage().getBytes();
			 byte[] Vbytes = receiveExchangeVO.getExchangeVImage().getBytes();
			 if(Hbytes.length != 0)
			 {
				 String originalFileName = receiveExchangeVO.getExchangeHImage().getOriginalFilename();
	             String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(HImagePath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 HImagePath = receiveExchangeVO.getExchangeName()+"_v"+ver+"_H"+originalFileExtension;
			 }
			 if(Hbytes.length != 0)
			 {
				 String originalFileName = receiveExchangeVO.getExchangeVImage().getOriginalFilename();
	             String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(VImagePath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 VImagePath = receiveExchangeVO.getExchangeName()+"_v"+ver+"_V"+originalFileExtension;
			 }
			if(apiService.modifyExchange(receiveExchangeVO,HImagePath,HImagePath))
			{
				if(Hbytes.length != 0)
				{
					Path Hpath = Paths.get(context.getRealPath("image/HExchange/") + HImagePath);
				    Files.write(Hpath, Hbytes);
				}
				if(Vbytes.length != 0)
				{
					Path Vpath = Paths.get(context.getRealPath("image/VExchange/") + VImagePath);
				    Files.write(Vpath, Vbytes);
				}
				return new ResponseEntity<>(GranConfig.RETURN_EXCHANGE_MODIFY_SECCESS,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(GranConfig.RETURN_EXCHANGE_MODIFY_FAIL,HttpStatus.BAD_REQUEST);
			}
       } catch (Exception e) {
    	   LOG.info(e.getMessage());
           return new ResponseEntity<>(GranConfig.RETURN_IMAGE_FAIL,HttpStatus.BAD_REQUEST);
       }
    }
	@RequestMapping(value = "/getexchange", method = RequestMethod.POST)
    public ResponseEntity<ExchangeDTO> getExchange(@RequestParam("exchangeID") int exchangeID)
	{
        return new ResponseEntity<>(apiService.getExchange(exchangeID),HttpStatus.OK);
    }
	@RequestMapping(value = "/registcompany", method = RequestMethod.POST)
    public ResponseEntity<String> registCompany(@ModelAttribute ReceiveCompanyVO receiveCompanyVO)
	{
		if(apiService.registCompany(receiveCompanyVO))
		{
			 return new ResponseEntity<>(GranConfig.RETURN_COMPANY_REGIST_SECCESS,HttpStatus.OK);
		}
        return new ResponseEntity<>(GranConfig.RETURN_COMPANY_REGIST_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/modifycompany", method = RequestMethod.POST)
    public ResponseEntity<String> modifyCompany(@ModelAttribute ReceiveCompanyVO receiveCompanyVO)
	{
		if(apiService.modifyCompany(receiveCompanyVO))
		{
			 return new ResponseEntity<>(GranConfig.RETURN_COMPANY_MODIFY_SECCESS,HttpStatus.OK);
		}
        return new ResponseEntity<>(GranConfig.RETURN_COMPANY_MODIFY_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/getcompany", method = RequestMethod.POST)
    public ResponseEntity<CompanyDTO> getCompany(@RequestParam("companyID") int companyID)
	{
        return new ResponseEntity<>(apiService.getCompany(companyID),HttpStatus.OK);
    }
	
	private String setManagementApp(Model model)
    {
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("companylist",apiService.getCompanyList());
    	model.addAttribute("applist",apiService.getAppList());
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
		model.addAttribute("app",apiService.getApp(appID));
		List<AppEventDTO> appEventList = apiService.getAppEventList(appID);
		for(int i=0;i<appEventList.size();i++)
		{
			if(appEventList.get(i).getAppEventEndTime().getTime() < System.currentTimeMillis() && appEventList.get(i).isAppEventEnable())// 시간지났고 enable이면.
			{
				apiService.disableAppEvent(appEventList.get(i).getAppEventID());
				appEventList.get(i).setAppEventEnable(false);
				LOG.info("setManagementAppEvent(ALREADY_EVENT_END) - AppID : " + appID+" /eventKey : "+appEventList.get(i).getAppEventKey());
				continue;
			}
		}
    	model.addAttribute("eventList",appEventList);
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
		// DTO -> VO 변경 필요... table과 너무 안맞게 되어있다... 브라우저 VO 따로 설계 / DTO db insert용으로만 바꾸기 모든 Select VO로 Return.
		List<UserInAppDTO> userInAppList = apiService.getUserInAppByUserID(userID);
		List<UserEventDTO> userEventList = apiService.getUserEventList(userID);
		for(int i=0;i<userInAppList.size();i++)
		{
			List<AppEventDTO> appEventList = apiService.getAppEventList(userInAppList.get(i).getAppID());
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
    	model.addAttribute("message", "Gran Monster");
    	return "redirect:/login";
    }
	private String setManagementSDK(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	return "managementsdk";
	}
}
