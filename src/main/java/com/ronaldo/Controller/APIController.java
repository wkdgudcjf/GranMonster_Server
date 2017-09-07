package com.ronaldo.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ronaldo.domain.UserVo;
import com.ronaldo.mapper.UserMapper;

@RestController
public class APIController {
	
	@Autowired
	private UserMapper userMapper;

    @RequestMapping(value = "/api/join", method = RequestMethod.GET)
    public List<UserVo> join(Model model){
    	List<UserVo> list = userMapper.getUserList();
        return list;
    }
    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public List<UserVo> login(Model model){
    	List<UserVo> list = userMapper.getUserList();
        return list;
    }
    @RequestMapping(value = "/api/charge", method = RequestMethod.GET)
    public List<UserVo> charge(Model model){
    	List<UserVo> list = userMapper.getUserList();
        return list;
    }
    @RequestMapping(value = "/api/use", method = RequestMethod.GET)
    public List<UserVo> use(Model model){
    	List<UserVo> list = userMapper.getUserList();
        return list;
    }
}
