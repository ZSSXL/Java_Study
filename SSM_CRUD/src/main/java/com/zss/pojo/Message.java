package com.zss.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回的类
 * @author 12711
 *
 */

public class Message {
	
	/**
	 * 状态码，如：200-成功，404-失败，500-出错
	 */
	private int code;
	
	/**
	 * 提示信息
	 */
	private String msg;
	
	/**
	 * 用户要返回给浏览器的数据
	 */
	private Map<String,Object> extend = new HashMap<String, Object>();

	public static Message success() {
		Message result = new Message();
		result.setCode(200);
		result.setMsg("SUCCESSED !");
		return result;
	}
	
	public static Message fail() {
		Message result = new Message();
		result.setCode(404);
		result.setMsg("FAILED !");
		return result;
	}
	
	public Message add(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
}
