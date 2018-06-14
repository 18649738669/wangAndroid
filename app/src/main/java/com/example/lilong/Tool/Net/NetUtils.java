package com.example.lilong.Tool.Net;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by long on 2018-4-12.
 */
public class NetUtils {

    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
     */
    public static byte[] encodeParameters(Map<String, Object> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue().toString(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }


    public static byte[] encodeParametersToJson(Map<String, Object> params, String paramsEncoding) {

        try {
            Gson gson = new Gson();

            String json = gson.toJson(params);
            return json.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, e);
        }
    }


}

