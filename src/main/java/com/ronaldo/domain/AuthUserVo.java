package com.ronaldo.domain;

public class AuthUserVo
{ 
	private String id;
	private String email;
	private String password;
	private String auth;
	public AuthUserVo(String id, String password, String email, String auth) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.auth = auth;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
