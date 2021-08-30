package com.grgbanking.counter.common.core.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MD5Util {
    /**
     * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5Util.class.getName() + "Init ERROR，MessageDigest do not suport MD5Util.");
			nsaex.printStackTrace();
		}
	}

	/**
	 * 字符串MD5加密.
	 * 
	 * @param
	 * @return
	 */
	public static String stringToMD5(String str) {

		try {
			byte[] strTemp = str.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			return toHexString(mdTemp.digest());
		} catch (NoSuchAlgorithmException e) {
			// logger.error("encryption algorithm error!", e);
			return null;
		}
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

	/**
	 * 获取文件MD5和文件大小
	 * 
	 * @param file
	 * @return filesize,md5
	 * @throws IOException
	 */
	public synchronized static String getFileMD5String(File file) {
		return getFileMD5(file);
	}

	public synchronized static String[] getFileSizeAndMD5String(File file) throws IOException {
		String[] rtstr = new String[2];
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new FileInputStream(file);
			long filelen = file.length();
			rtstr[0] = String.valueOf(filelen);
			out = new ByteArrayOutputStream((int) filelen);
			byte[] cache = new byte[1048576];
			for (int i = in.read(cache); i != -1; i = in.read(cache)) {
				out.write(cache, 0, i);
			}
			out.flush();
			messagedigest.update(out.toByteArray());
			rtstr[1] = bufferToHex(messagedigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return rtstr;
	}

	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte[] bytes) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte[] bytes, int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();

		// 2EA3E66AC37DF7610F5BD322EC4FFE48 670M 11s kuri双核1.66G 2G内存
		File big = new File("C:/test11112.log");
		String md5 = getFileMD5(big);
		System.out.println(md5);

		// String[] filesize_md5 = getFileSizeAndMD5String(big);
		//
		long end = System.currentTimeMillis();
		System.out.println(((end - begin) / 1000) + "s");
		// System.out.println("length:" + filesize_md5[0] + "    md5:"
		// + filesize_md5[1] + " time:" + ((end - begin) / 1000) + "s");
	}

	public static String getFileMD5(File file) {
		if (!file.isFile())
			return null;
		MessageDigest digest = null;
		FileInputStream in = null;
        byte[] buffer = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		String md5 = "00000000000000000000000000000000" + bigInt.toString(16);

		return md5.substring(md5.length() - 32);
	}

	public static Map<String, String> getDirMD5(File file, boolean listChild) {
		if (!file.isDirectory())
			return null;
		// <filepath,md5>
		Map<String, String> map = new HashMap<String, String>();
		String md5;
        File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory() && listChild) {
					map.putAll(getDirMD5(f, listChild));
				} else {
					md5 = getFileMD5(f);
					if (md5 != null) {
						map.put(f.getPath(), md5);
					}
				}
			}
		}
		return map;
	}

}