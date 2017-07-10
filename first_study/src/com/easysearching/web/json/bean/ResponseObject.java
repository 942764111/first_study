package com.easysearching.web.json.bean;

import net.sf.json.JSONObject;

public class ResponseObject {

	private boolean success;

	private String responseCode;

	private String responseInfo;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(String responseInfo) {
		this.responseInfo = responseInfo;
	}

	public static void main(String[] args) {
		ResponseObject response = new ResponseObject();
		response.setSuccess(true);
		response.setResponseCode("001");
		//response.setResponseInfo("too many files");
		JSONObject jo = JSONObject.fromObject(response);
		//JSONObject jo = JSONObject.fromObject(response);
		System.out.println(jo.toString());

	}
}
