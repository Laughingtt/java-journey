package com.sdgft.udf;

import cn.hutool.crypto.digest.DigestUtil;
import com.aliyun.odps.udf.UDF;

public final class Sha256Crypto extends UDF {

    public Sha256Crypto() {

    }

    // Sha256 crypto
    public String evaluate(String s) {
        if (s == null) {
            return null;
        } else {
            return DigestUtil.sha256Hex(s);
        }
    }

    public static void main(String[] args) {
        Sha256Crypto udf = new Sha256Crypto();
        System.out.println(udf.evaluate("123"));
    }

}