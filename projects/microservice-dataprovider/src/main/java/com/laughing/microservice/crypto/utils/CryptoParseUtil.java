package com.laughing.microservice.crypto.utils;

import com.bestpay.util.AesUtils;
import com.dsjzx.sm4.crypto.impl.SM4Util;
import com.laughing.microservice.common.exception.Sm4DecryptException;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;


/**
 * @author linld
 */
@Slf4j
public class CryptoParseUtil {

    private static SM4Util sm4Util = new SM4Util();


    /**
     * 使用SM4密钥对身份证号码就行加密
     */
    public static String sm4Encrypt(String idCardNum, String sm4Key) {
        Instant start = Instant.now();

        String encrypt = sm4Util.encrypt(idCardNum, sm4Key);

        Instant end = Instant.now();

        String logStr = LoggerCreator.logReqEnc(start, end, "SM4_ENC");

        log.info(logStr);

        if (encrypt == null){
            throw new Sm4DecryptException("Sm4 key or input message error , can`t encrypt");
        }

        return encrypt;
    }

    /**
     * 使用SM4密钥对身份证号码就行加密
     */
    public static String sm4Decrypt(String idCardNum, String sm4Key) {
        Instant start = Instant.now();

        String decrypt = sm4Util.decrypt(idCardNum, sm4Key);

        Instant end = Instant.now();

        String logStr = LoggerCreator.logReqEnc(start, end, "SM4_DEC");

        log.info(logStr);

        if (decrypt == null){
            throw new Sm4DecryptException("Sm4 key or input message error , can`t decrypt");
        }

        return decrypt;
    }

    /**
     * 对业务参数进行加密封装成body
     */
    public static String aesEncrypt(String serviceParamJson, String serviceKey) throws Exception {

        Instant start = Instant.now();

        String encrypt = AesUtils.doAesEncrypt(serviceParamJson, serviceKey);

        Instant end = Instant.now();

        String logStr = LoggerCreator.logReqEnc(start, end, "AES_ENC");
        log.info(logStr);

        return encrypt;
    }

    /**
     * aes解密
     */
    public static String aesDecrypt(String cipherRsp, String serviceKey) throws Exception {
        Instant start = Instant.now();

        String aesDecrypt = AesUtils.doAesDecrypt(cipherRsp, serviceKey);

        Instant end = Instant.now();

        String logStr = LoggerCreator.logReqEnc(start, end, "AES_DEC");
        log.info(logStr);

        return aesDecrypt;

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String key = "abcd";
        String token = "8e44bda3fca31c1ceec9bb080b1926ff";
        long timestamp = System.currentTimeMillis();

        String serviceKey = "1693313263804" + token + key;

        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] digest = md.digest(serviceKey.getBytes());
        byte[] digest = md.digest(serviceKey.getBytes(StandardCharsets.UTF_8));


        String _128BitKey_ = new String(digest);
//        String _128BitKey = "48jxyu6f15rzqr3d";
        String _128BitKey = "93c84a50070bba26";
        System.out.println(digest.toString());

        String idCardNumber = "31031020230527310x";
        String encodeStr = sm4Encrypt(idCardNumber, _128BitKey);
        System.out.println(encodeStr);
        System.out.println(sm4Decrypt(encodeStr, _128BitKey));
    }

}
