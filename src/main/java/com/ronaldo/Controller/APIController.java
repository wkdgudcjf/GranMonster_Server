package com.ronaldo.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.ronaldo.config.GranConfig;
import com.ronaldo.dao.AppDAO;
import com.ronaldo.dao.AppListDAO;
import com.ronaldo.dao.ExchangeDAO;
import com.ronaldo.dao.ExchangeListDAO;
import com.ronaldo.dao.ExhaustDAO;
import com.ronaldo.dao.PayloadDAO;
import com.ronaldo.dao.PurchaseDAO;
import com.ronaldo.dao.ReturnUserDAO;
import com.ronaldo.dao.UserDAO;
import com.ronaldo.domain.AppVo;
import com.ronaldo.domain.ExchangeVo;
import com.ronaldo.domain.UserVo;
import com.ronaldo.service.ApiServiceImpl;

@RestController
public class APIController {

	@Autowired
	private ApiServiceImpl apiService;

	JSONParser jsonParser = new JSONParser();

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<ReturnUserDAO> login(@RequestBody String param) {
		JSONObject jsonObject;
		String userKey, appKey = null;
		UserVo userVo = null;
		UserDAO userDAO = null;
		ReturnUserDAO returnUserDAO = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			returnUserDAO = new ReturnUserDAO();
			userDAO = new UserDAO();
			// 로그인 이벤트가 켜져있다면 로그인 이벤트 코인 올려주고 이벤트 보상 처리.
			// 로그인 횟수,연속로그인 처리는 추후에..
			// 이것도 로그인 지네가 횟수나 뭐 연속로그인 새고 달성했을경우에 이벤트로만 보내주는게 맞는거 아닌지?..
			if (apiService.getAppByKey(appKey) == null) {
				returnUserDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(returnUserDAO, HttpStatus.BAD_REQUEST);
			}
			userVo = apiService.getUser(userKey);
			if (userVo == null) // 모든 앱에서 처음 가입이면.
			{
				if (apiService.registUser(userKey)) // 일단 유저 등록시키고.
				{
					apiService.registUserInApp(userKey, appKey);
					userVo = apiService.getUser(userKey);
				} else {
					returnUserDAO.setState(GranConfig.RETURN_USER_LOGIN_FAIL);
					return new ResponseEntity<>(returnUserDAO, HttpStatus.BAD_REQUEST);
				}
			} else {
				if (apiService.getUserInApp(userKey, appKey) == null) // 앱 처음이면 앱 등록시킨다.
				{
					apiService.registUserInApp(userKey, appKey);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(returnUserDAO, HttpStatus.BAD_REQUEST);
		}
		userDAO.setUserCoin(userVo.getUserCoin());
		userDAO.setUserEmail(userVo.getUserEmail());
		userDAO.setUserMoney(userVo.getUserMoney());
		returnUserDAO.setUserDAO(userDAO);
		returnUserDAO.setState(GranConfig.RETURN_USER_LOGIN_SUCCESS);
		return new ResponseEntity<>(returnUserDAO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coinCharge", method = RequestMethod.POST)
	public ResponseEntity<String> coinCharge(@RequestParam("param") String param) {
		String result = "success";
		JSONObject jsonObject;
		String userKey, coin = null, money = null, appKey, eventKey = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			eventKey = (String) jsonObject.get("eventKey");
			if (Integer.parseInt(eventKey) > 2) {
				coin = "5"; // 앱 이벤트 매퍼로 얻어온다.
				// 만약 이벤트
				money = "0";
			} else {
				coin = (String) jsonObject.get("coin");
				money = (String) jsonObject.get("money");
			}
			// 만약 이벤트키가 1일경우 충전이므로 구글 영수증 검사를 한 후 추가해줌.
			// 또한 이벤트키가 1이 아닐경우 유저가 이미 이벤트를 진행해서 보상을 받았는지 확인하고.
			// 보상을 안받았으면 보상을 받았다고 UserEvent에 체크해줘야한다.
			// 또한 이벤트가 인원제한이 있을경우 확인.
			// apiService.addBilling(userKey, appKey, Integer.parseInt(coin),
			// Integer.parseInt(money),Integer.parseInt(eventKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coinUse", method = RequestMethod.POST)
	public ResponseEntity<String> coinUse(@RequestParam("param") String param) {
		String result = "success";
		JSONObject jsonObject;
		String userKey, coin, appKey;
		try {
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			coin = (String) jsonObject.get("coin");
			apiService.minusBilling(userKey, appKey, Integer.parseInt(coin), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "fail";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/applist", method = RequestMethod.POST)
	public ResponseEntity<AppListDAO> applist(@RequestBody String param) {
		JSONObject jsonObject;
		String userKey = null, appKey = null;
		List<AppVo> appList = null;
		List<AppDAO> returnAppList = null;
		AppListDAO appListDAO = null;
		try {
			appListDAO = new AppListDAO();
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			if (apiService.getAppByKey(appKey) == null) {
				appListDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(appListDAO, HttpStatus.BAD_REQUEST);
			}
			// 이벤트 리스트랑 유저가 이 앱의 이벤트 성공했는지 도전중인지에 대한 여부까지 같이 리턴해줘야 한다.
			// 우선 다른 앱의 이벤트 성공/도전 여부는 생략하기로 한다. 아니면 각 앱에서 항상 최신 데이터를 보내줘야 하는데 그건 불가능하다.
			// 유저가 다른 앱을 접속해야만 갱신이 되기 때문. 물론 파라미터로 다 기록하면 되지만 중간에 끼어든 이벤트
			// (예를들면 스코어)에 대한 기록은 없으므로 이미 달성했는지 도전해야하는지 여부를 알 수 없다.
			// 만약 다 알고 싶으면 항상 서버에서 정해놓은 값(스코어,레벨,모은 돈....) 으로만 이벤트를 해야하며 물론 정해놓은 값들은 앱에서
			// 갱신될때마다 서버에 보내줘야한다.
			// apiService.minusBilling(userKey, appKey, Integer.parseInt(coin));
			appList = apiService.getEnableAppList(true);
			returnAppList = new ArrayList<AppDAO>();
			for (int i = 0; i < appList.size(); i++) {
				AppDAO appDAO = new AppDAO();
				appDAO.setAppImagePath(appList.get(i).getAppImagePath());
				appDAO.setAppName(appList.get(i).getAppName());
				appDAO.setAppPackage(appList.get(i).getAppPackage());
				appDAO.setAppURL(appList.get(i).getAppURL());
				// event 확인하기 (userKey로)
				appDAO.setAppEventList(apiService.getAppEventList(appList.get(i).getAppID()));
				returnAppList.add(appDAO);
			}
			appListDAO.setAppList(returnAppList);
			// 이벤트 기한은 서버에서는 적용만하고 앱에서 노출x 처리하도록.
			// 서버에서는 기한이 지나면 Disable 하는 코드 적용.
			appListDAO.setState(GranConfig.RETURN_APP_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appListDAO.setState(GranConfig.RETURN_APP_FAIL);
			return new ResponseEntity<>(appListDAO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(appListDAO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/payload", method = RequestMethod.POST)
	public ResponseEntity<PayloadDAO> payload(@RequestBody String param) {
		JSONObject jsonObject;
		String userKey = null, appKey = null;
		PayloadDAO payloadDAO = null;
		try {
			payloadDAO = new PayloadDAO();
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			if (apiService.getAppByKey(appKey) == null) {
				payloadDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(payloadDAO, HttpStatus.BAD_REQUEST);
			}

			String payload = apiService.setUserPayload(appKey, userKey);
			payloadDAO.setUserPayload(payload);
			payloadDAO.setState(GranConfig.RETURN_APP_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			payloadDAO.setState(GranConfig.RETURN_APP_FAIL);
			return new ResponseEntity<>(payloadDAO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(payloadDAO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/exchange", method = RequestMethod.POST)
	public ResponseEntity<ExchangeListDAO> stock(@RequestBody String param) {
		JSONObject jsonObject;
		String userKey = null, appKey = null;
		ExchangeListDAO exchangeListDAO = null;
		List<ExchangeVo> exchangeList = null;
		List<ExchangeDAO> returnExchangeList = null;
		try {
			exchangeListDAO = new ExchangeListDAO();
			
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			if (apiService.getAppByKey(appKey) == null) {
				exchangeListDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(exchangeListDAO, HttpStatus.BAD_REQUEST);
			}
			
			exchangeList = apiService.getEnableExchangeList(true);
			returnExchangeList = new ArrayList<ExchangeDAO>();
			
			for (int i = 0; i < exchangeList.size(); i++) {
				ExchangeDAO exchangeDAO = new ExchangeDAO();
				exchangeDAO.setCoin(exchangeList.get(i).getExchangeCoin());
				exchangeDAO.setPrice(exchangeList.get(i).getExchangeMoney());
				returnExchangeList.add(exchangeDAO);
			}
			
			exchangeListDAO.setExchangeList(returnExchangeList);
			exchangeListDAO.setState(GranConfig.RETURN_APP_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exchangeListDAO.setState(GranConfig.RETURN_APP_FAIL);
			return new ResponseEntity<>(exchangeListDAO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(exchangeListDAO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/purchase", method = RequestMethod.POST)
	public ResponseEntity<PurchaseDAO> purchase(@RequestBody String param) {
		JSONObject jsonObject;
		String userKey = null, appKey = null, coin = null, money = null, payload = null;
		String productId = null, purchaseToken = null;
		PurchaseDAO purchaseDAO = null;
		try {
			purchaseDAO = new PurchaseDAO();
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			coin = (String) jsonObject.get("coin");
			money = (String) jsonObject.get("money");
			payload = (String) jsonObject.get("payload");
			productId = (String) jsonObject.get("productId");
			purchaseToken = (String) jsonObject.get("purchaseToken");
			
			String userpayload = apiService.getUserPayload(userKey);
			
			if (!(userpayload.equals(payload))) {
				purchaseDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(purchaseDAO, HttpStatus.BAD_REQUEST);
			}
			AppVo appVo = apiService.getAppByKey(appKey);
			if (appVo == null) {
				purchaseDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(purchaseDAO, HttpStatus.BAD_REQUEST);
			}
			String emailAddress = "temp";

			JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
					.setJsonFactory(JSON_FACTORY).setServiceAccountId(emailAddress)
					.setServiceAccountPrivateKeyFromP12File(new File("granmonster-160fa837dc4c.p12"))
					.setServiceAccountScopes(Collections.singleton("https://www.googleapis.com/auth/androidpublisher"))
					.build();

			String packageName = appVo.getAppPackage();
			
			AndroidPublisher publisher = new AndroidPublisher.Builder(httpTransport, JSON_FACTORY, credential)
					.setApplicationName(packageName).build();
			
			AndroidPublisher.Purchases.Products.Get get = publisher.purchases().products().get(packageName, productId,
					purchaseToken);
			ProductPurchase productPurchase = get.execute();
			System.out.println(productPurchase.toPrettyString());

			// 인앱 상품의 소비 상태. 0 아직 소비 안됨(Yet to be consumed) / 1 소비됨(Consumed)
			Integer consumptionState = productPurchase.getConsumptionState();

			// 개발자가 지정한 임의 문자열 정보
			String developerPayload = productPurchase.getDeveloperPayload();

			// 구매 상태. 0 구매완료 / 1 취소됨
			Integer purchaseState = productPurchase.getPurchaseState();

			// 상품이 구매된 시각. 타임스탬프 형태
			Long purchaseTimeMillis = productPurchase.getPurchaseTimeMillis();

			apiService.addBilling(userKey, appKey, Integer.parseInt(coin), Integer.parseInt(money), true);

			purchaseDAO.setUserPayload(payload);
			purchaseDAO.setState(GranConfig.RETURN_APP_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			purchaseDAO.setState(GranConfig.RETURN_APP_FAIL);
			return new ResponseEntity<>(purchaseDAO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(purchaseDAO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/exhaust", method = RequestMethod.POST)
	public ResponseEntity<ExhaustDAO> exhaust(@RequestParam("param") String param) {
		ExhaustDAO exhaustDAO = null;
		JSONObject jsonObject;
		String userKey = null, appKey = null, coin = null, payload = null;
		try {
			exhaustDAO = new ExhaustDAO();
			jsonObject = (JSONObject) jsonParser.parse(param);
			userKey = (String) jsonObject.get("userKey");
			appKey = (String) jsonObject.get("appKey");
			coin = (String) jsonObject.get("coin");
			payload = (String) jsonObject.get("payload");

			String userpayload = apiService.getUserPayload(userKey);
			if (!(userpayload.equals(payload))) {
				exhaustDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(exhaustDAO, HttpStatus.BAD_REQUEST);
			}
			if (apiService.getAppByKey(appKey) == null) {
				exhaustDAO.setState(GranConfig.RETURN_APP_KEY_FAIL);
				return new ResponseEntity<>(exhaustDAO, HttpStatus.BAD_REQUEST);
			}
			apiService.minusBilling(userKey, appKey, Integer.parseInt(coin), false);
			exhaustDAO.setState(GranConfig.RETURN_APP_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exhaustDAO.setState(GranConfig.RETURN_APP_FAIL);
			return new ResponseEntity<>(exhaustDAO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(exhaustDAO, HttpStatus.OK);
	}
}
