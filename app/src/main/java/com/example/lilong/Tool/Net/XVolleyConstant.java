package com.example.lilong.Tool.Net;

/**
 * Created by long on 2018-4-12.
 */
public class XVolleyConstant {

    public static final String NEW_LINE_STR = "\r\n";

    public static final byte[] NEW_LINE_STR_BYTE = "\r\n".getBytes();


    /**
     * 文本参数的Content-type
     */
    public static final byte[] CONTENT_TYPE_TEXT_PLAIN = "Content-Type: text/plain; charset=UTF-8\r\n".getBytes();

    /**
     * 字节流参数的Content-type
     */
    public static final byte[] CONTENT_TYPE_OCTET_STREAM = "Content-Type: application/octet-stream\r\n".getBytes();


    /**
     * 二进制参数
     */
    public static final byte[] ENCODING_BINARY = "Content-Transfer-Encoding: binary\r\n\r\n".getBytes();

    /**
     * 文本参数
     */
    public static final byte[] ENCODING_BIT = "Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes();


}
