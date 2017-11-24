package com.ronaldo.dao;

public class ExchangeDAO {
	private int exchangeMoney;
	private int exchangeCoin;
	private String exchangeName;
	private String exchangeImagePath;
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