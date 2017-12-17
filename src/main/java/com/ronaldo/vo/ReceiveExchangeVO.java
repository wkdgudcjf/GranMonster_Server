package com.ronaldo.vo;

import org.springframework.web.multipart.MultipartFile;

public class ReceiveExchangeVO {
	private int exchangeMoney;
	private int exchangeCoin;
	private String exchangeKey;
	private String exchangeName;
	private MultipartFile exchangeImage;
	private int exchangeID;
	private boolean exchangeEnable;
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
	public String getExchangeKey() {
		return exchangeKey;
	}
	public void setExchangeKey(String exchangeKey) {
		this.exchangeKey = exchangeKey;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public MultipartFile getExchangeImage() {
		return exchangeImage;
	}
	public void setExchangeImage(MultipartFile exchangeImage) {
		this.exchangeImage = exchangeImage;
	}
	public int getExchangeID() {
		return exchangeID;
	}
	public void setExchangeID(int exchangeID) {
		this.exchangeID = exchangeID;
	}
	public boolean isExchangeEnable() {
		return exchangeEnable;
	}
	public void setExchangeEnable(boolean exchangeEnable) {
		this.exchangeEnable = exchangeEnable;
	}
	
}