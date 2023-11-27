package com.sdgft.udf;

import cn.hutool.crypto.SmUtil;
import com.aliyun.odps.udf.UDF;

// sm3 crypto
public class Sm3Crypto extends UDF {

    public Sm3Crypto() {

    }

    public String evaluate(String s) {
        if (s == null) {
            return null;
        }
        return SmUtil.sm3().digestHex(s);
    }

    public static void main(String[] args) {
        Sm3Crypto sm3Crypto = new Sm3Crypto();
        String digestHex = sm3Crypto.evaluate("hello");
        System.out.println(digestHex);

    }
}