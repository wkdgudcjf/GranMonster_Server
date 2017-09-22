package com.ronaldo.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ronaldo.mapper.BillingMapper;
import com.ronaldo.domain.UserVo;
import com.ronaldo.mapper.AppMapper;
import com.ronaldo.mapper.CompanyMapper;
import com.ronaldo.mapper.UserMapper;
import com.ronaldo.service.ApiServiceImpl;

@RestController
public class APIController {
	
	@Autowired
	private ApiServiceImpl apiService;
	
	JSONParser jsonParser = new JSONParser();
	
    @RequestMapping(value = "/api/join", method = RequestMethod.POST)
    public ResponseEntity<String> join(Model model,@RequestParam("param") String param){
    	String result = "success";
    	JSONObject jsonObject;
    	String key = null;
    	System.out.println(param);
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			key = (String) jsonObject.get("key");
			System.out.println(key);
			apiService.registUser(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<UserVo> login(Model model,@RequestParam("param") String param){
    	JSONObject jsonObject;
    	String key = null;
    	UserVo userVo = null;
    	System.out.println(param);
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			key = (String) jsonObject.get("key");
			System.out.println(key);
			userVo = apiService.getUser(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(userVo, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(userVo, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/charge", method = RequestMethod.POST)
    public ResponseEntity<String> charge(Model model,@RequestParam("param") String param){
    	String result = "success";
    	JSONObject jsonObject;
    	String key = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			key = (String) jsonObject.get("key");
			apiService.registUser(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/use", method = RequestMethod.POST)
    public ResponseEntity<String> use(Model model,@RequestParam("param") String param){
    	String result = "success";
    	JSONObject jsonObject;
    	String key = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			key = (String) jsonObject.get("key");
			apiService.registUser(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
