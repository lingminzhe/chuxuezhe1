package com.grgbanking.counter.common.core.encrypt;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @Author:grgbanking
 * @Date:2021/8/4 2:00 下午
 */
public class JasyptUtil {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
//        textEncryptor.setPassword("d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb");
        textEncryptor.setPassword("123");
        //要加密的数据
//        String encrypt = textEncryptor.encrypt("redis@App2020");
        String decrypt = textEncryptor.decrypt("vW+Nup9LaTfIFwgufUBsYg==");
//        System.out.println("加密后的密文："+encrypt);
        System.out.println("解密后的密文："+decrypt);
    }

}
