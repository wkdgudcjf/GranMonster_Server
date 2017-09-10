package com.ronaldo.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ronaldo.domain.UserVo;
import com.ronaldo.mapper.UserMapper;

@RestController
public class APIController {
	
	@Autowired
	private UserMapper userMapper;

    @RequestMapping(value = "/api/join", method = RequestMethod.POST)
    public ResponseEntity<String> join(Model model,@RequestParam("id") String id){
    	System.out.println("log : " + id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public List<UserVo> login(Model model){
    	List<UserVo> list = userMapper.getUserList();
        return list;
    }
    @RequestMapping(value = "/api/charge", method = RequestMethod.POST)
    public List<UserVo> charge(Model model){
    	List<UserVo> list = userMapper.getUserList();
        return list;
    }
    @RequestMapping(value = "/api/use", method = RequestMethod.POST)
    public List<UserVo> use(Model model){
    	List<UserVo> list = userMapper.getUserList();
        return list;
    }
}
