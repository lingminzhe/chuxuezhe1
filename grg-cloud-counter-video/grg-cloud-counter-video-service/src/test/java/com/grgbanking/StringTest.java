package com.grgbanking;

/**
 * <p>@date : 2020/4/21</p>
 * <p>@description : </p>
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "/abc/123/abc/123/abc";
        System.out.println(str.replace("/abc", ""));
        System.out.println(str.replaceAll("/abc", ""));
        System.out.println(str.replaceAll("^/abc", ""));
    }
}
