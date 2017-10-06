package com.ronaldo.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.ronaldo.domain.AppEventVo;
import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.CompanyVo;
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
	@RequestMapping(value = "/managementcompany", method = RequestMethod.GET)
    public String managementcompany(Model model){
    	if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementCompany(model);
    }
	@RequestMapping(value = "/registapp", method = RequestMethod.POST)
    public ResponseEntity<String> registApp(@RequestParam("appPackage") String appPackage
    		, @RequestParam("appURL") String appURL,@RequestParam("companyID") int companyID,
    		@RequestParam("appName") String appName,@RequestParam("appImage") MultipartFile appImage) {
        try {
        	 // Get the file and save it uploads dir
        	 byte[] bytes = appImage.getBytes();
        	 String originalFileName = appImage.getOriginalFilename();
             String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
             Path path = Paths.get(context.getRealPath("image/") + appName+"_v1"+originalFileExtension);
             if(apiService.registApp(appName,companyID, appURL, appName+"_v1"+originalFileExtension, appPackage))
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
	@RequestMapping(value = "/modifyapp", method = RequestMethod.POST)
    public ResponseEntity<String> modifyApp(@RequestParam("appID") int appID,@RequestParam("appPackage") String appPackage
    		, @RequestParam("appURL") String appURL,@RequestParam("companyID") int companyID,
    		@RequestParam("appName") String appName,@RequestParam("appImage") MultipartFile appImage
    		,@RequestParam("appEnable") boolean appEnable) {
		 try {
        	 // Get the file and save it uploads dir
			 String ImagePath = apiService.getApp(appID).getAppImagePath();
			 byte[] bytes = appImage.getBytes();
			 if(bytes.length != 0)
			 {
				 String originalFileName = appImage.getOriginalFilename();
	             String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				 StringTokenizer stk = new StringTokenizer(ImagePath,"_v");
				 String str1 = stk.nextToken();
				 String str2 = stk.nextToken();
				 int ver = str2.charAt(0)-48;
				 ver++;
				 ImagePath = appName+"_v"+ver+originalFileExtension;
			 }
			if(apiService.modifyApp(appID,appName,companyID, appURL, ImagePath, appPackage,appEnable))
			{
				if(bytes.length != 0)
				{
					Path path = Paths.get(context.getRealPath("image/") + ImagePath);
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
	@RequestMapping(value = "/getapp", method = RequestMethod.POST)
    public ResponseEntity<AppVo> getApp(@RequestParam("appID") int appID) {
        return new ResponseEntity<>(apiService.getApp(appID),HttpStatus.OK);
    }
	@RequestMapping(value = "/getappevent", method = RequestMethod.POST)
    public ResponseEntity<AppEventVo> getAppEvent(@RequestParam("appEventID") int appEventID) {
        return new ResponseEntity<>(apiService.getAppEvent(appEventID),HttpStatus.OK);
    }
	@RequestMapping(value = "/managementappevent", method = RequestMethod.POST)
    public String managementappevent(Model model,@RequestParam("appID") int appID) {
		if(sessionWire.getId()==null)
		{
    		return setRedirectLogin(model);
		}
    	return setManagementAppEvent(model,appID);
    }
	@RequestMapping(value = "/registappevent", method = RequestMethod.POST)
    public ResponseEntity<String> registappevent(@RequestParam("appKey") int appKey,@RequestParam("appID") int appID,
    		@RequestParam("appEventContent") String appEventContent
    		, @RequestParam("appEventCoin") int appEventCoin) {
        if(apiService.registAppEvent(appID,appEventContent,appEventCoin,appKey))
        {
            return new ResponseEntity<>(GranConfig.RETURN_APP_REGIST_SECCESS,HttpStatus.OK);
        }
        return new ResponseEntity<>(GranConfig.RETURN_APP_FAIL,HttpStatus.BAD_REQUEST);
    }
	@RequestMapping(value = "/modifyappevent", method = RequestMethod.POST)
    public ResponseEntity<String> modifyappevent(@RequestParam("appEventID") int appEventID,
    		@RequestParam("appEventContent") String appEventContent
    		, @RequestParam("appEventCoin") int appEventCoin,
    		@RequestParam("appEventEnable") boolean appEventEnable) {
		if(apiService.modifyAppEvent(appEventID,appEventContent,appEventCoin,appEventEnable))
		{
			 return new ResponseEntity<>(GranConfig.RETURN_COMPANY_MODIFY_SECCESS,HttpStatus.OK);
		}
        return new ResponseEntity<>(GranConfig.RETURN_COMPANY_FAIL,HttpStatus.BAD_REQUEST);
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
		model.addAttribute("size",list.get(list.size()-1).getAppEventKey()+1);
    	model.addAttribute("eventList",list);
    	return "managementappevent";
    }
	private String setManagementUser(Model model)
    {
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("userlist",apiService.getUserList());
    	return "managementuser";
    }
	private String setManagementBilling(Model model)
    {
    	model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
    	model.addAttribute("billinglist",apiService.getBillingList());
    	return "managementbilling";
    }
	private String setRedirectLogin(Model model)
    {
    	model.addAttribute("message", "그랑몬스터");
    	return "redirect:/login";
    }
}
