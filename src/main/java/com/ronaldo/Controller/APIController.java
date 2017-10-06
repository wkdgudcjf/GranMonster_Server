package com.ronaldo.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<String> join(@RequestBody String param){
    	String result = "success";
    	JSONObject jsonObject;
    	String userKey,appKey = null;
    	System.out.println(param);
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey"); 
			// 나중에 app에 이벤트연결. 아님 이거 안받고 모든 이벤트 연결해줘야됨. 
			// 다운로드 이벤트가 켜져있다면 다운르도 이벤트 코인 올려주고 이벤트 보상 처리.
			// 근데 다운로드 이벤트가 켜져있는지 어떻게 알지..? 그건 그냥 앱에서 최초 로그인이면 자기네들이 알아서 파라미터로 보내줘야하는게 맞는듯함...
			// appinuser 연결.
			System.out.println(userKey);
			if(apiService.registUser(userKey))
			{
				 return new ResponseEntity<>(result, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
    }
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<UserVo> login(@RequestBody String param){
    	JSONObject jsonObject;
    	String userKey,appKey = null;
    	UserVo userVo = null;
    	System.out.println(param);
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			// 로그인 이벤트가 켜져있다면 로그인 이벤트 코인 올려주고 이벤트 보상 처리.
			// 로그인 횟수,연속로그인 처리는 추후에..
			// 이것도 로그인 지네가 횟수나 뭐 연속로그인 새고 달성했을경우에 이벤트로만 보내주는게 맞는거 아닌지?..
			System.out.println(appKey);
			userVo = apiService.getUser(userKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(userVo, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(userVo, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/coinCharge", method = RequestMethod.POST)
    public ResponseEntity<String> coinCharge(Model model,@RequestParam("param") String param){
    	String result = "success";
    	JSONObject jsonObject;
    	String userKey,coin = null,money = null,appKey,eventKey = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			eventKey = (String) jsonObject.get("eventKey");
			if(Integer.parseInt(eventKey)>2)
			{
				coin = "5"; // 앱 이벤트 매퍼로 얻어온다. 
				money = "0";
			}
			else
			{
				coin = (String) jsonObject.get("coin");
				money = (String) jsonObject.get("money");
			}
			//만약 이벤트키가 1일경우 충전이므로 구글 영수증 검사를 한 후 추가해줌.
			// 또한 이벤트키가 1이 아닐경우 유저가 이미 이벤트를 진행해서 보상을 받았는지 확인하고.
			// 보상을 안받았으면 보상을 받았다고 UserEvent에 체크해줘야한다.
			// 또한 이벤트가 인원제한이 있을경우 확인.
			apiService.addBilling(userKey, appKey, Integer.parseInt(coin), Integer.parseInt(money),Integer.parseInt(eventKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/coinUse", method = RequestMethod.POST)
    public ResponseEntity<String> coinUse(Model model,@RequestParam("param") String param){
    	String result = "success";
    	JSONObject jsonObject;
    	String userKey,coin,appKey,eventKey = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			coin = (String) jsonObject.get("coin");
			eventKey = (String) jsonObject.get("eventKey");
			apiService.minusBilling(userKey, appKey, Integer.parseInt(coin),Integer.parseInt(eventKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/appList", method = RequestMethod.POST)
    public ResponseEntity<String> appList(Model model,@RequestParam("param") String param){
    	String result = "success";
    	JSONObject jsonObject;
    	String userKey,coin,appKey = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			coin = (String) jsonObject.get("coin");
			// 이벤트 리스트랑 유저가 이 앱의 이벤트 성공했는지 도전중인지에 대한 여부까지 같이 리턴해줘야 한다.
			// 우선 다른 앱의 이벤트 성공/도전 여부는 생략하기로 한다. 아니면 각 앱에서 항상 최신 데이터를 보내줘야 하는데 그건 불가능하다.
			// 유저가 다른 앱을 접속해야만 갱신이 되기 때문. 물론 파라미터로 다 기록하면 되지만 중간에 끼어든 이벤트
			//(예를들면 스코어)에 대한 기록은 없으므로 이미 달성했는지 도전해야하는지 여부를 알 수 없다.
			// 만약 다 알고 싶으면 항상 서버에서 정해놓은 값(스코어,레벨,모은 돈....) 으로만 이벤트를 해야하며 물론 정해놓은 값들은 앱에서 갱신될때마다 서버에 보내줘야한다.
			//apiService.minusBilling(userKey, appKey, Integer.parseInt(coin));
			
			// 이벤트 기한은 서버에서는 적용만하고 앱에서 노출x 처리하도록.
			// 서버에서는 기한이 지나면 Disable 하는 코드 적용.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
