package com.laughing.crypto_parse_bridge.cryptoparsebridge.flow;

import com.alibaba.fastjson.JSONObject;
import com.laughing.crypto_parse_bridge.cryptoparsebridge.protol.CryptoParse;
import com.laughing.crypto_parse_bridge.cryptoparsebridge.utils.LogRegistration;
import org.apache.log4j.Logger;

public class ResponseParse {
    Logger logger = LogRegistration.get_logger();


    public final String response_parse(JSONObject rspJson, CryptoParse crypto_parse) throws Exception {

        JSONObject json = new JSONObject();

        String plainTextRsp = null;
        if (rspJson.getBoolean("success")) {
            String cipherRsp = rspJson.getString("result");
            JSONObject plainJsonRsp = JSONObject.parseObject(crypto_parse.aes_decrypt(cipherRsp));
            logger.info("解密结果：" + plainJsonRsp);
            json.put("code", plainJsonRsp.get("code"));
            json.put("message", plainJsonRsp.get("message"));
            json.put("success", true);
            plainJsonRsp.remove("code");
            plainJsonRsp.remove("message");
            if (!plainJsonRsp.isEmpty()) {
                json.put("result", plainJsonRsp);
            }

        } else {
            json.put("code", rspJson.get("errorCode"));
            json.put("success", false);
            json.put("result", null);
            json.put("message", rspJson.get("errorMsg"));
        }
        plainTextRsp = json.toJSONString();
        logger.info("响应结果：" + plainTextRsp);
        return plainTextRsp;
    }
}
