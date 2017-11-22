package com.ronaldo.dao;

import java.util.List;

public class ExchangeListDAO {
	private List<ExchangeDAO> exchangeList;
	private String state;
	
	public List<ExchangeDAO> getExchangeList() {
		return exchangeList;
	}
	public void setExchangeList(List<ExchangeDAO> exchangeList) {
		this.exchangeList = exchangeList;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}