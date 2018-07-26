package com.lppz.ehr.bean;

import java.util.Date;

public class Log {

	private Long id;
	
	private String ip;

	private String url;

	private String method;

	private String operator;

	private String params;

	private String request;

	private String response;

	private short code;

	private Date create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public short getCode() {
		return code;
	}

	public void setCode(short code) {
		this.code = code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getRequest() {
		return request;
	}

	@Override
	public String toString() {
		return "Log{" +
				"id=" + id +
				", ip='" + ip + '\'' +
				", url='" + url + '\'' +
				", method='" + method + '\'' +
				", operator='" + operator + '\'' +
				", params='" + params + '\'' +
				", request='" + request + '\'' +
				", response='" + response + '\'' +
				", code=" + code +
				", create_time=" + create_time +
				'}';
	}

	public void setRequest(String request) {
		this.request = request;
	}


}
