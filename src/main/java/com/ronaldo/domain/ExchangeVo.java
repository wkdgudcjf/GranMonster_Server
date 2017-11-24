package com.ronaldo.domain;

public class ExchangeVo {
    private int exchangeID;
    private int exchangeMoney;
    private int exchangeCoin;
    private boolean exchangeEnable;
    private String exchangeName;
    private String exchangeImagePath;
	public int getExchangeID() {
		return exchangeID;
	}
	public void setExchangeID(int exchangeID) {
		this.exchangeID = exchangeID;
	}
	public int getExchangeMoney() {
		return exchangeMoney;
	}
	public void setExchangeMoney(int exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}
	public int getExchangeCoin() {
		return exchangeCoin;
	}
	public void setExchangeCoin(int exchangeCoin) {
		this.exchangeCoin = exchangeCoin;
	}
	public boolean isExchangeEnable() {
		return exchangeEnable;
	}
	public void setExchangeEnable(boolean exchangeEnable) {
		this.exchangeEnable = exchangeEnable;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public String getExchangeImagePath() {
		return exchangeImagePath;
	}
	public void setExchangeImagePath(String exchangeImagePath) {
		this.exchangeImagePath = exchangeImagePath;
	}
    
}