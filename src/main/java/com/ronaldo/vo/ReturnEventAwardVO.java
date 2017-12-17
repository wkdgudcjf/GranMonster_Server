package com.ronaldo.vo;

import com.ronaldo.config.ErrorCodeConfig.EventAwardEnum;

public class ReturnEventAwardVO {
	private int coin;
	private EventAwardEnum state;
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public EventAwardEnum getState() {
		return state;
	}
	public void setState(EventAwardEnum state) {
		this.state = state;
	}
	
}