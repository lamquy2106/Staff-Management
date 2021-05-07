package com.example.demo.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String userName;
	private String Staffmail;
	private List<String> roles;
	public JwtResponse(String token, Long id, String userName, String staffmail, List<String> roles) {
		this.token = token;
		this.id = id;
		this.userName = userName;
		Staffmail = staffmail;
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStaffmail() {
		return Staffmail;
	}
	public void setStaffmail(String staffmail) {
		Staffmail = staffmail;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
