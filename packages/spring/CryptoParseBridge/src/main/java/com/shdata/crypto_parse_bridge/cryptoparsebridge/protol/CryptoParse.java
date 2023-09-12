package com.shdata.crypto_parse_bridge.cryptoparsebridge.protol;

import com.bestpay.util.AesUtils;
import com.dsjzx.sm4.crypto.impl.SM4Util;
import com.shdata.crypto_parse_bridge.cryptoparsebridge.conf.ConstructorParameters;

public class CryptoParse {

    private String serviceKey;
    private String sm4Key;
    private String secureId;
    SM4Util sm4Util = new SM4Util();

    public CryptoParse(ConstructorParameters constructorParameters) {
        this.serviceKey = constructorParameters.getServiceKey();
        this.sm4Key = constructorParameters.getSm4Key();
        this.secureId = constructorParameters.getSecureId();
    }

    // 使用SM4密钥对身份证号码就行加密
    public final String sm4_encrypt(String idCardNum) {

        return sm4Util.encrypt(idCardNum, sm4Key);
    }

    // 对业务参数进行加密封装成body
    public final String aes_encrypt(String serviceParamJson) throws Exception {

        return AesUtils.doAesEncrypt(serviceParamJson, serviceKey);
    }

    // aes解密
    public final String aes_decrypt(String cipherRsp) throws Exception {

        return AesUtils.doAesDecrypt(cipherRsp, serviceKey);

    }

    public String getSecureId() {
        return secureId;
    }

}
