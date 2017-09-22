package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.CompanyVo;

@Mapper
public interface  CompanyMapper{
	 public CompanyVo getCompany(int companyID);
	 public List<CompanyVo> getCompanyList();
	 public List<CompanyVo> getEnableCompanyList(boolean appEnable);
	 public void registCompany(CompanyVo companyVo);
	 public void deleteCompany(int companyID);
	 public void updateCompany(CompanyVo companyVo);
}