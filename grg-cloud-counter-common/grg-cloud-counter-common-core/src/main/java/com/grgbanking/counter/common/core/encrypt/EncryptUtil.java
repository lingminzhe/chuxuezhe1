/**
 * 
 */
package com.grgbanking.counter.common.core.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * 
 * SHA256
 * 
 * @author Sandy
 * 
 * @date 2021年6月19日 下午1:31:11
 *
 * @version 1.0 2021年6月19日 Created
 * 
 * @copyright Copyright © 2021 广电运通 All rights reserved.
 */
public class EncryptUtil {

	private static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

	public static String getFileSHA256(InputStream inputStream) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA-256");
			byte[] buffer = new byte[1024];
			int numRead = 0;
			while ((numRead = inputStream.read(buffer)) > 0) {
				mdTemp.update(buffer, 0, numRead);
			}
			return toHexString(mdTemp.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("encryption exception", e);

		} catch (IOException e) {
			logger.error("encryption exception", e);
		}

		return null;
	
	}

	public static String getFileSHA256(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			logger.error("getFileSHA256",e);
			return null;
		}

		String sha256 = toHexString(digest.digest());

		return sha256;
	}

	private static String toHexString(byte[] md) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < md.length; i++) {
			if ((md[i] & 0xff) < 0x10) {
				sb.append("0");
			}

			sb.append(Long.toString(md[i] & 0xff, 16));
		}
		return sb.toString();
	}

	public static String strToSHA256(String str) {
		try {
			byte[] strTemp = str.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("SHA-256");
			mdTemp.update(strTemp);
			return toHexString(mdTemp.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("encryption algorithm error!", e);
			return null;
		}
	}
}
