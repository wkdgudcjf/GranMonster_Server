package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.BillingVo;

@Mapper
public interface  BillingMapper{
	public List<BillingVo> getBillingList();
	public List<BillingVo> getUserBillingList(int userID);
	public List<BillingVo> getAppBillingList(int appID);
	public void registBilling(BillingVo billingVo) throws Exception;
}