package com.example.lilong.Tool.Net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lilong.Tool.Utils.LogUtils;
import com.example.lilong.Tool.Utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class BasicRequest extends JsonObjectRequest {

	public BasicAPI basicAPI;

	public BasicRequest(BasicAPI basicAPI,Response.Listener<JSONObject> successListener,
						Response.ErrorListener errorListener ){
		super(basicAPI.getHttpType(), basicAPI.getUrl(),null, successListener, errorListener);
		LogUtils.e("[url]"+basicAPI.getUrl());
		this.basicAPI = basicAPI;

	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return basicAPI.getHeaders();
	}


	@Override
	public byte[] getBody() {
		return basicAPI.getBody();
	}

	/**
	 * 重写parseNetworkResponse方法，将返回的数据格式化位UTF-8
	 *
	 * @param response
	 * @return
	 */
	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String je = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			String temp = new String(response.data, basicAPI.getParamsEncoding());

			return Response.success(new JSONObject(temp), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException var3) {
			return Response.error(new ParseError(var3));
		} catch (JSONException var4) {
			return Response.error(new ParseError(var4));
		}
	}


	@Override
	public String getBodyContentType() {

		if (StringUtils.isNoEmpty(basicAPI.getDiyBodyContentType())) {
			return basicAPI.getDiyBodyContentType();
		}

//		return super.getBodyContentType();
		return String.format("application/x-www-form-urlencoded; charset=%s", "utf-8");
	}

	//	@Override
//	public RetryPolicy getRetryPolicy() {
//		RetryPolicy retryPolicy = new DefaultRetryPolicy(50 * 1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//		return retryPolicy;
//	}
}