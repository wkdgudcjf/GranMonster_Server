package com.ronaldo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ronaldo.domain.CompanyVo;

@Mapper
public interface  CompanyMapper{
	 public CompanyVo getCompany(int companyID);
	 public List<CompanyVo> getCompanyList();
	 public List<CompanyVo> getEnableCompanyList(boolean appEnable);
	 public void registCompany(CompanyVo companyVo) throws Exception;
	 public void deleteCompany(int companyID) throws Exception;
	 public void updateCompany(CompanyVo companyVo) throws Exception;
}