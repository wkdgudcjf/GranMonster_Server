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
import com.ronaldo.mapper.AppMapper;
import com.ronaldo.mapper.CompanyMapper;
import com.ronaldo.mapper.UserMapper;

@RestController
public class APIController {
	
	
	JSONParser jsonParser = new JSONParser();
	
    @RequestMapping(value = "/api/join", method = RequestMethod.POST)
    public ResponseEntity<String> join(Model model,@RequestParam("json") String json){
    	System.out.println("log : " + json);
    	JSONObject jsonObject;
    	String id = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			id = (String) jsonObject.get("id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(Model model,@RequestParam("json") String json){
    	System.out.println("log : " + json);
    	JSONObject jsonObject;
    	String id = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			id = (String) jsonObject.get("id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/charge", method = RequestMethod.POST)
    public ResponseEntity<String> charge(Model model,@RequestParam("json") String json){
    	System.out.println("log : " + json);
    	JSONObject jsonObject;
    	String id = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			id = (String) jsonObject.get("id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/use", method = RequestMethod.POST)
    public ResponseEntity<String> use(Model model,@RequestParam("json") String json){
    	System.out.println("log : " + json);
    	JSONObject jsonObject;
    	String id = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			id = (String) jsonObject.get("id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
