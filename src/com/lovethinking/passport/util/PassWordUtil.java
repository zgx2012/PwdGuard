package com.lovethinking.passport.util;

public class PassWordUtil {
    private static final String sAESKey = "0123456789ABCEDF";

    public static String encrypt(String sSrc) throws Exception {
        return AES.Encrypt(sSrc, sAESKey);
    }

    public static String decrypt(String sSrc) throws Exception {
        return AES.Decrypt(sSrc, sAESKey);
    }

    /*
    public static String simpleEncryption(String str) {
        MyLog.i("str = " + str);
        String encryptedStr;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            byte b = (byte) chars[i];
            b = (byte) ((b << 4) | (b >> 4));
            chars[i] = (char) b;
        }
        encryptedStr = String.valueOf(chars);
        MyLog.i("encryptedStr = " + encryptedStr);
        return encryptedStr;
    }

    public static String simpleDecryption(String str) {
        return simpleEncryption(str);
    }
    */

}
