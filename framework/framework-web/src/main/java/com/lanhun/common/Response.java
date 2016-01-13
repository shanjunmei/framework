/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.common;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月5日 下午3:14:45
 * @version
 */
public class Response {

    private String code;

    private String msg;

    private Object data;

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    public Object getData() {
	return data;
    }

    public void setData(Object data) {
	this.data = data;
    }

    public static Response ok() {
	Response response = ok(null, null);
	return response;
    }

    public static Response ok(String msg, Object data) {
	Response response = build("1", msg, data);
	return response;
    }
    public static Response ok( Object data) {
   	Response response = ok(null, data);
   	return response;
       }

    public static Response build(String code, String msg, Object data) {
	Response response = new Response();
	response.setCode(code);
	response.setMsg(msg);
	response.setData(data);
	return response;
    }

    public static Response error( String msg) {
   	Response response = error(msg, null);
   	return response;
       }
    public static Response error(String msg, Object data) {
	Response response = build("-1", msg, data);
	return response;
    }

    public static Response error() {
	Response response = error(null, null);
	return response;
    }

}
