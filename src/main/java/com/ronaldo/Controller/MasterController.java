package com.ronaldo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronaldo.config.SessionWire;
import com.ronaldo.service.AuthUserServiceImpl;

@Controller
public class MasterController {
	
	@Autowired
	private AuthUserServiceImpl userService;
	@Autowired
	SessionWire sessionWire;
	@RequestMapping(value={"/agreement"}, method = RequestMethod.GET)
    public String agreement(Model model){
        return "agreement";
    }
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String loginGet(Model model){
		if(sessionWire.getId()!=null)
		{
			return "admin";
		}
		setMain(model);
        return "login";
    }
	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
    public String loginPost(Model model,@RequestParam("id") String id, @RequestParam("password") String password)
	{
		if(userService.isVaild(id,password))
		{
			sessionWire.setId(id);
			setAdmin(model);
			return "admin";
		}
		setLogin(model);
        return "login";
    }
	@RequestMapping(value={"/join"}, method = RequestMethod.GET)
    public String joinGet(Model model){
		model.addAttribute("message", "");
        return "join";
    }
	@RequestMapping(value={"/join"}, method = RequestMethod.POST)
    public String joinPost(Model model,@RequestParam("id") String id, @RequestParam("password") String password,
    		@RequestParam("email") String email,@RequestParam("inputNumberCheck") String inputNumberCheck){
		if(userService.createUser(id,password,email,inputNumberCheck))
		{
			setMain(model);
    		return "login";
		}
		setJoin(model);
        return "join";
    }
	@RequestMapping(value={"/logout"}, method = RequestMethod.GET)
    public String logout(Model model){
		if(sessionWire.getId()!=null)
		{
			sessionWire.invaildate();
		}
        return "login";
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model){
    	if(sessionWire.getId()==null)
		{
    		setMain(model);
    		return "login";
		}
    	setAdmin(model);
    	return "admin";
    }
    private void setMain(Model model)
    {
    	model.addAttribute("message", "그랑몬스터");
    }
    private void setLogin(Model model)
    {
    	model.addAttribute("message", "아이디 비밀번호를 확인하세요");
    }
    private void setAdmin(Model model)
    {
    	model.addAttribute("user",userService.searchUser(sessionWire.getId()));
    }
    private void setJoin(Model model)
    {
    	model.addAttribute("message", "동일한 id가 존재하거나 인증번호가 틀립니다.");
    }
}
