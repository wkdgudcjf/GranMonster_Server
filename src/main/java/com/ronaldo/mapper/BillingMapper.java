package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.BillingDTO;

@Mapper
public interface  BillingMapper{
	public List<BillingDTO> getBillingList();
	public List<BillingDTO> getUserBillingList(int userID);
	public List<BillingDTO> getAppBillingList(int appID);
	public void registBilling(BillingDTO billingDTO) throws Exception;
}