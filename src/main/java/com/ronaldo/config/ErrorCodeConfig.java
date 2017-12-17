package com.ronaldo.config;

public class ErrorCodeConfig
{
	public enum LoginEnum
	{
		NOT_EXIST_APPKEY,
		USER_KEY_INVALID,
		USER_ALREADY_JOIN_APP,
		SUCCESS;
	}
	public enum AppListEnum
	{
		NOT_EXIST_APPKEY,
		SUCCESS;
	}
	public enum PayloadEnum
	{
		NOT_EXIST_APPKEY,
		USER_KEY_INVALID,
		SUCCESS;
	}
	public enum ExchangeEnum
	{
		NOT_EXIST_APPKEY,
		SUCCESS;
	}
	public enum PurchaseEnum
	{
		NOT_EXIST_APPKEY,
		NOT_EQUAL_PAYLOAD,
		INVALID_BILLING,
		SUCCESS;
	}
	public enum ExhaustEnum
	{
		NOT_EXIST_APPKEY,
		NOT_EQUAL_PAYLOAD,
		NOT_ENOUGH_COIN,
		INVALID_BILLING,
		SUCCESS;
	}
	public enum EventEnum
	{
		NOT_EXIST_APPKEY,
		NOT_REGIST_EVENT,
		NOT_ENABLE_EVENT,
		ALREADY_SUCCESS_EVENT,
		INVALID_EVENT,
		SUCCESS;
	}
	public enum EventAwardEnum
	{
		NOT_EXIST_APPKEY,
		NOT_EXIST_EVENT,
		NOT_ACHIEVE_EVENT,
		NOT_ENABLE_EVENT,
		ALREADY_REWARD_EVENT,
		INVALID_USER,
		INVALID_BILLING,
		SUCCESS;
	}
}
