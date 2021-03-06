package com.soho.spring.utils;

import java.security.MessageDigest;

/**
 * @author shadow
 */
public class MD5Utils {

    private final static String MD5_KEY = "*w$MH^$4eZK&6!JV";

    public final static String encrypt(String s, String salt) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            if (salt != null) {
                s += salt;
            }
            byte[] btInput = s.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String s) {
        return encrypt(s, null);
    }

    public final static String MD5PBK(String s) {
        return encrypt(s, MD5_KEY);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("123456test"));
    }

}