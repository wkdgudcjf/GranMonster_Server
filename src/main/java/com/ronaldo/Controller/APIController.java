package com.ronaldo.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ronaldo.service.ApiService;
import com.ronaldo.vo.ReceiveAppListVO;
import com.ronaldo.vo.ReceiveEventRewardVO;
import com.ronaldo.vo.ReceiveEventVO;
import com.ronaldo.vo.ReceiveExchangeAPIVO;
import com.ronaldo.vo.ReceiveExhaustVO;
import com.ronaldo.vo.ReceivePayloadVO;
import com.ronaldo.vo.ReceivePurchaseVO;
import com.ronaldo.vo.ReceiveUserVO;
import com.ronaldo.vo.ReceiveVisibleVO;
import com.ronaldo.vo.ReturnAppListVO;
import com.ronaldo.vo.ReturnEventRewardVO;
import com.ronaldo.vo.ReturnEventVO;
import com.ronaldo.vo.ReturnExchangeListVO;
import com.ronaldo.vo.ReturnExhaustVO;
import com.ronaldo.vo.ReturnPayloadVO;
import com.ronaldo.vo.ReturnPurchaseVO;
import com.ronaldo.vo.ReturnUserVO;
import com.ronaldo.vo.ReturnVisibleVO;

@RestController
public class APIController {
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<ReturnUserVO> login(@RequestBody ReceiveUserVO receiveUserVO)
	{
		ReturnUserVO returnUserVO = new ReturnUserVO();

		apiService.login(receiveUserVO,returnUserVO);
		
		return new ResponseEntity<>(returnUserVO, HttpStatus.OK);
		// Only OK. Because state... hmm.. anyway communication ok..
	}

	@RequestMapping(value = "/api/visible", method = RequestMethod.POST)
	public ResponseEntity<ReturnVisibleVO> visible(@RequestBody ReceiveVisibleVO receiveVisibleVO)
	{
		ReturnVisibleVO returnVisibleVO = new ReturnVisibleVO();

		apiService.visible(receiveVisibleVO,returnVisibleVO);
		
		return new ResponseEntity<>(returnVisibleVO, HttpStatus.OK);
		// Only OK. Because state... hmm.. anyway communication ok..
	}
	
	@RequestMapping(value = "/api/applist", method = RequestMethod.POST)
	public ResponseEntity<ReturnAppListVO> applist(@RequestBody ReceiveAppListVO receiveAppListVO) 
	{
		ReturnAppListVO appListDAO =  new ReturnAppListVO();
		
		apiService.appList(receiveAppListVO,appListDAO);
		
		return new ResponseEntity<>(appListDAO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/payload", method = RequestMethod.POST)
	public ResponseEntity<ReturnPayloadVO> payload(@RequestBody ReceivePayloadVO receivePayloadVO) {
		ReturnPayloadVO returnPayloadVO = new ReturnPayloadVO();

		apiService.payload(receivePayloadVO,returnPayloadVO);
		
		return new ResponseEntity<>(returnPayloadVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/exchange", method = RequestMethod.POST)
	public ResponseEntity<ReturnExchangeListVO> exchange(@RequestBody ReceiveExchangeAPIVO receiveExchangeAPIVO) {
		ReturnExchangeListVO exchangeListDAO = new ReturnExchangeListVO();
		
		apiService.exchange(receiveExchangeAPIVO,exchangeListDAO);
		
		return new ResponseEntity<>(exchangeListDAO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/purchase", method = RequestMethod.POST)
	public ResponseEntity<ReturnPurchaseVO> purchase(@RequestBody ReceivePurchaseVO receivePurchaseVO) {
		ReturnPurchaseVO returnPurchaseVO =  new ReturnPurchaseVO();
		
		apiService.purchase(receivePurchaseVO,returnPurchaseVO);
		
		return new ResponseEntity<>(returnPurchaseVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/exhaust", method = RequestMethod.POST)
	public ResponseEntity<ReturnExhaustVO> exhaust(@RequestBody ReceiveExhaustVO receiveExhaustVO) {
		ReturnExhaustVO returnExhaustVO = new ReturnExhaustVO();
		
		apiService.exhaust(receiveExhaustVO,returnExhaustVO);
		
		return new ResponseEntity<>(returnExhaustVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/event", method = RequestMethod.POST)
	public ResponseEntity<ReturnEventVO> event(@RequestBody ReceiveEventVO receiveEventVO) {
		ReturnEventVO returnEventVO = new ReturnEventVO();
		
		apiService.event(receiveEventVO,returnEventVO);
		
		return new ResponseEntity<>( returnEventVO, HttpStatus.OK);
	}
	@RequestMapping(value = "/api/eventreward", method = RequestMethod.POST)
	public ResponseEntity<ReturnEventRewardVO> eventreward(@RequestBody ReceiveEventRewardVO receiveEventRewardVO) {
		ReturnEventRewardVO returnEventRewardVO = new ReturnEventRewardVO();
		
		apiService.eventReward(receiveEventRewardVO,returnEventRewardVO);
		
		return new ResponseEntity<>(returnEventRewardVO, HttpStatus.OK);
	}
}
