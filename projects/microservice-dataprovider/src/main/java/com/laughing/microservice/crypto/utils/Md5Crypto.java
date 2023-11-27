package com.laughing.microservice.crypto.utils;

import cn.hutool.crypto.digest.MD5;

public class Md5Crypto {

    public static String encode(String keyFactor) {
        return MD5.create().digestHex16(keyFactor);
    }

    public static String encode32(String keyFactor) {
        return MD5.create().digestHex(keyFactor);
    }

    public static void main(String[] args) {
        String keyFactor = "hello";
        String decodeKey = MD5.create().digestHex16(keyFactor);
        String decodeKey2 = MD5.create().digestHex(keyFactor);
        System.out.println(decodeKey);
        System.out.println(decodeKey2);
    }
}
