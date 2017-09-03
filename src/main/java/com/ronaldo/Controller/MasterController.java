package com.ronaldo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronaldo.repository.UserRepository;

@Controller
public class MasterController {
	
	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
    	model.addAttribute("result", userRepository.getUserList());
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody String test(){
        return "test";
    }
}
