package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.BillingVo;

@Mapper
public interface  BillingMapper{
	 public List<BillingVo> getBillingList();
	 public void registBilling(BillingVo companyVo);
	 public void deleteBilling(int companyID);
}