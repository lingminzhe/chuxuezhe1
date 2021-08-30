/**
 * 文件名：EncodeUtils.java
 * 创建日期： 2012-5-30
 * Copyright (c) 2011-2011 广电运通
 * All rights reserved.
 
 * 修改记录：
 * 1.修改时间, 修改人：
 *   修改内容：
 */
package com.grgbanking.counter.common.core.encrypt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * 各种格式的编码加码工具类.
 * 
 * @author calvin
 */
public class EncodeUtils {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	/**
	 * Hex编码.
	 */
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	public static String Encrypt(String strSrc, String encName) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * Base64编码.
	 * 
	 * @return 编码后字符
	 */
	public static String base64Encode(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(对URL不支持的字符如+,/,=转为其他字符, 见RFC3548).
	 * 
	 * @return 编码后字符
	 */
	public static String base64UrlEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 编码.
	 * 
	 * @param input
	 *            字符串
	 * @param encoding
	 *            字符集
	 * @return String
	 */
	public static String urlEncode(String input, String encoding) {
		try {
			return URLEncoder.encode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 * 
	 * @param input
	 *            字符串
	 * @return String
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 解码.
	 * 
	 * @param input
	 *            字符串
	 * @param encoding
	 *            字符集
	 * @return String
	 */
	public static String urlDecode(String input, String encoding) {
		try {
			return URLDecoder.decode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * Xml转码.
	 * 
	 * @param xml
	 *            xml
	 * @return Xml转码
	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 反转码.
	 * 
	 * @param xmlEscaped
	 *            转码后Xml
	 * @return xml
	 */
	public static String xtmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

}
