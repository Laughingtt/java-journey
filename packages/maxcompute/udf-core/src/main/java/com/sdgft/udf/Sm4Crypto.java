package com.sdgft.udf;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.aliyun.odps.udf.UDF;

import java.util.Arrays;

public class Sm4Crypto extends UDF {
    SymmetricCrypto sm4;

    Sm4Crypto() {
        sm4 = SmUtil.sm4();
    }

    // sm3 crypto
    public String evaluate(String content) {

        if (null == content)
        {
            return null;
        }else {
            return encodeHex(content);
        }
    }

    public String encodeHex(String content) {

        return sm4.encryptHex(content);
    }

    public String decodeHex(String content) {
        return sm4.decryptStr(content);
    }

    public static void Sm4SetKenEnc(){
        String content = "hello";
        SM4 sm41 = SmUtil.sm4();
        System.out.println(Arrays.toString(sm41.getSecretKey().getEncoded()));
        String enc = sm41.encryptHex(content);

        System.out.println(enc);
        SM4 sm42 = SmUtil.sm4(sm41.getSecretKey().getEncoded());

        System.out.println(sm42.decryptStr(enc));
    }

    public static void main(String[] args) {
        String content = "hello";

        Sm4Crypto sm4Crypto = new Sm4Crypto();
        String s = sm4Crypto.encodeHex(content);
        System.out.println("encode : " + s);

        String s1 = sm4Crypto.decodeHex(s);
        System.out.println("decode : " + s1);

        Sm4SetKenEnc();

    }
}