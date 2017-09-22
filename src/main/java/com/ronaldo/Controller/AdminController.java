package com.ronaldo.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

import com.ronaldo.config.SessionWire;
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
		{;
    		return setRedirectLogin(model);
		}
    	return setManagementCompany(model);
    }
	@RequestMapping(value = "/registapp", method = RequestMethod.POST)
    public ResponseEntity<String> registApp(@RequestParam("appPackage") String appPackage
    		, @RequestParam("appURL") String appURL,@RequestParam("companyID") String companyID,
    		@RequestParam("appName") String appName,@RequestParam("appImage") MultipartFile appImage) {
        try {
        	 // Get the file and save it uploads dir
        	 byte[] bytes = appImage.getBytes();
        	 String originalFileName = appImage.getOriginalFilename();
             String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
             Path path = Paths.get(context.getRealPath("image/") + appName+"_v1"+originalFileExtension);
             Files.write(path, bytes);
            apiService.registApp(appName,Integer.parseInt(companyID), appURL, appName+"_v1"+originalFileExtension, appPackage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
	@RequestMapping(value = "/modifyapp", method = RequestMethod.POST)
    public ResponseEntity<String> modifyApp(@RequestParam("appID") int appID,@RequestParam("appPackage") String appPackage
    		, @RequestParam("appURL") String appURL,@RequestParam("companyID") String companyID,
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
			     Path path = Paths.get(context.getRealPath("image/") + ImagePath);
			     Files.write(path, bytes);
			 }
			 apiService.modifyApp(appID,appName,Integer.parseInt(companyID), appURL, ImagePath, appPackage,appEnable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
	@RequestMapping(value = "/getapp", method = RequestMethod.POST)
    public ResponseEntity<AppVo> getApp(@RequestParam("appID") int appID) {
        return new ResponseEntity<>(apiService.getApp(appID),HttpStatus.OK);
    }
	@RequestMapping(value = "/registcompany", method = RequestMethod.POST)
    public ResponseEntity<String> registCompany(@RequestParam("companyName") String companyName) {
		apiService.registCompany(companyName);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
	@RequestMapping(value = "/modifycompany", method = RequestMethod.POST)
    public ResponseEntity<String> modifyCompany(@RequestParam("companyName") String companyName,
    		@RequestParam("companyID") int companyID,@RequestParam("companyEnable") boolean companyEnable) {
		apiService.modifyCompany(companyID,companyName,companyEnable);
        return new ResponseEntity<>("success",HttpStatus.OK);
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
