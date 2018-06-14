package com.example.lilong.Tool.Net;

import android.text.TextUtils;

import com.android.volley.Request;
import com.example.lilong.Tool.Utils.MapUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class BasicAPI {

	public Gson gson = new Gson();

	public int HttpType = Request.Method.POST;

	public abstract String getUrl();

	public int getHttpType() {
		return HttpType;
	}


	public Map<String,Object> params = new HashMap<>();


	/**
	 * 网络请求的参数
	 * @param key
	 * @param value
	 */
	public void addParams(String key, Object value){
		if(value == null){
			return;
		}
		if (value instanceof String){
			if(TextUtils.isEmpty(value.toString())){
				return;
			}
		}

		try{
			params.put(key, value);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * header
	 */
	public Map<String, String> headers = new HashMap<String, String>();


	/**
	 * add header
	 *
	 * @param key   Header's key
	 * @param value Header's value
	 */
	public void addHeader(String key, String value) {
		MapUtils.add(headers, key, value);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}


	public void delHeader(String key) {

		if (headers.containsKey(key)) {
			headers.remove(key);
		}

	}
	/**
	 * get all param
	 *
	 * @return all param as Map<String,String>
	 */
	public Map<String,Object> getParams() {
		return params;
	}

	/**
	 * encoding of params, default "UTF-8"
	 *
	 * @return String
	 */
	public String getParamsEncoding() {
		return "UTF-8";
	}

	/**
	 * param's type ， such as raw , form-data or DIY
	 *
	 * @return ParamType
	 */
	public ParamType getParamType() {
		return ParamType.formData;
	}

	/**
	 * 获取Body
	 *
	 * @return
	 */
	public byte[] getBody() {

		Map<String, Object> params = getParams();

		switch (getParamType()) {
			//如果是以raw方式传参,默认为json
			case raw:

				//把表单参数转为body
				return NetUtils.encodeParametersToJson(params, getParamsEncoding());

			//以表单方式传参
			case formData:

				//把表单参数转为body
				return NetUtils.encodeParameters(params, getParamsEncoding());

			case DIY:

				return getDiyBody();
		}

		return null;
	}


	/**
	 * 自定义body
	 */
	private byte[] diyBody = null;

	/**
	 * 自定义body的content-type
	 */
	private String diyBodyContentType ;

	public byte[] getDiyBody() {
		return diyBody;
	}

	public void setDiyBody(byte[] diyBody) {
		this.diyBody = diyBody;
	}

	public String getDiyBodyContentType() {
		return diyBodyContentType;
	}

	public void setDiyBodyContentType(String diyBodyContentType) {
		this.diyBodyContentType = diyBodyContentType;
	}


	public abstract void success(JSONObject data);

	public abstract void error(long code,String msg);
}