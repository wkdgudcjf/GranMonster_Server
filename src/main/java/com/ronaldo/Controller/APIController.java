package com.ronaldo.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ronaldo.domain.UserVo;
import com.ronaldo.repository.UserRepository;

@RestController
public class APIController {
	
	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = "/JoinUser", method = RequestMethod.GET)
    public List<UserVo> index(Model model){
    	List<UserVo> list = userRepository.getUserList();
        return list;
    }
}
