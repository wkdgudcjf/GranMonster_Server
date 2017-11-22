package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.ExchangeVo;

@Mapper
public interface  ExchangeMapper{
	 public ExchangeVo getExchange(int exchangeID);
	 public List<ExchangeVo> getExchangeList();
	 public List<ExchangeVo> getEnableExchangeList(boolean exchangeEnable);
	 public void registExchange(ExchangeVo exchangeVo) throws Exception;
	 public void deleteExchange(int exchangeID) throws Exception;
	 public void updateExchange(ExchangeVo exchangeVo) throws Exception;
}