package com.tams.util;



import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginUtil {
	
	
	private final static String LOGINNAME = "loginname";
	
	private final static String PASSWORD = "password";

	private Integer loginname;
	
	private String password;	
	
	private Boolean isCookie;
	
	private Integer checkCode;
	
	private String userType;
	
	private HttpServletResponse response;
	
	private HttpServletRequest request;


	
	public String PASSWORD() {
		
		return PASSWORD;
	}
	
    public String LOGINNAME() {
		
		return LOGINNAME;
	}
	
	public Integer getLoginname() {
		return loginname;
	}
	public void setLoginname(Integer loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsCookie() {
		return isCookie;
	}
	public void setIsCookie(Boolean isCookie) {
		this.isCookie = isCookie;
	}
	
	public Integer getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(Integer checkCode) {
		this.checkCode = checkCode;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
	
	public static Map<String, Object> setCorrect( Integer code , Object url) {
	    
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("url", url);
		
		return map;
	}
	
	public static Map<String, Object> setError( Integer code , Object msg) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg",msg);
		
		return map;	
	}
	

	@Override
	public String toString() {
		return "LoginUtil [loginname=" + loginname + ", password=" + password + ", isCookie=" + isCookie
				+ ", checkCode=" + checkCode + ", userType=" + userType + "]";
	}
	
	
}
