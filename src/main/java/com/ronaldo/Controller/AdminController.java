package com.ronaldo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ronaldo.config.SessionWire;
import com.ronaldo.service.AuthUserServiceImpl;
import com.ronaldo.service.ApiServiceImpl;
@Controller
public class AdminController
{ 
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
    		return setInitLogin(model);
		}
    	return setManagementApp(model);
    }
	@RequestMapping(value = "/managementcompany", method = RequestMethod.GET)
    public String managementcompany(Model model){
    	if(sessionWire.getId()==null)
		{
    		return setInitLogin(model);
		}
    	return setManagementCompany(model);
    }
	private String setManagementApp(Model model)
    {
    	model.addAttribute("user",userService.searchUser(sessionWire.getId()));
    	return "managementapp";
    }
	private String setManagementCompany(Model model)
    {
    	model.addAttribute("user",userService.searchUser(sessionWire.getId()));
    	return "managementcompany";
    }
	private String setInitLogin(Model model)
    {
    	model.addAttribute("message", "그랑몬스터");
    	return "login";
    }
}
