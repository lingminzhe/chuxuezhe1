package com.grgbanking.counter.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

/**
 * @Author:mcyang
 * @Date:2021/6/29 上午10:35
 */
@Slf4j
public class FileUtil {

    /**
     * 生成随机文件名
     *
     * @return
     */
    public static String randomFileName() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int max=40,min=32;
        int length = (int) (Math.random()*(max-min)+min);
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 获取上传文件的md5
     *
     * @param file
     * @return
     */
    public static String getFileMd5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
        return null;
    }



}
