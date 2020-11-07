package com.lrh.common.spring.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/30
 */
public class MD5Util {
    public static String getMD5(byte[] bytes) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] str = new char[32];
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] tmp = md.digest();
            int k = 0;

            for (int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return new String(str);
    }

    public static String getMD5(String value) {
        return getMD5(value, StandardCharsets.UTF_8);
    }

    public static String getMD5(String value, Charset charset) {
        return getMD5(value.getBytes(charset));
    }
}
