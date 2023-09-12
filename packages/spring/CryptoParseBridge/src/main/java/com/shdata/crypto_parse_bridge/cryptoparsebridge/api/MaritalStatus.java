package com.shdata.crypto_parse_bridge.cryptoparsebridge.api;

import com.alibaba.fastjson.JSONObject;
import com.shdata.crypto_parse_bridge.cryptoparsebridge.conf.ConstructorParameters;
import com.shdata.crypto_parse_bridge.cryptoparsebridge.conf.ShdataDistributionConf;
import com.shdata.crypto_parse_bridge.cryptoparsebridge.flow.RequestUrl;
import com.shdata.crypto_parse_bridge.cryptoparsebridge.flow.ResponseParse;
import com.shdata.crypto_parse_bridge.cryptoparsebridge.protol.CryptoParse;
import com.shdata.crypto_parse_bridge.cryptoparsebridge.utils.LogRegistration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/marital-status/")
public class MaritalStatus {

    @Autowired
    private Environment env;
    Logger logger = LogRegistration.get_logger();

    RequestUrl requestUrl = new RequestUrl();
    ResponseParse responseParse = new ResponseParse();


    @RequestMapping("/single-verify")
    public String single_verify(@RequestParam(name = "idCardNum", required = true) String idCardNum,
                                @RequestParam(name = "name", required = true) String name,
                                @RequestParam(name = "maritalType", required = true) String maritalType) throws Exception {

        logger.info("Request start =======");

        // 初始化配置
        ConstructorParameters constructorParameters = new ConstructorParameters();
        constructorParameters.construct_key(env, ShdataDistributionConf.API_URL, ShdataDistributionConf.SINGLE_MARITAL_STATUS);
        CryptoParse crypto_parse = new CryptoParse(constructorParameters);


        Map<String, Object> serviceParam = new HashMap<String, Object>();
        // 版本参数
        serviceParam.put("version", constructorParameters.getVersion());
        serviceParam.put("scene", constructorParameters.getScene());
        serviceParam.put("realUser", constructorParameters.getRealUser());

        // 业务参数
        String idCipherText = crypto_parse.sm4_encrypt(idCardNum);
        serviceParam.put("name", name);
        serviceParam.put("maritalType", maritalType);
        serviceParam.put("idCardNum", idCipherText);
        String serviceParamJson = JSONObject.toJSONString(serviceParam);
        logger.info("业务参数:" + serviceParamJson);

        // 请求
        String StringEntityBody = requestUrl.constitute_body(serviceParamJson, crypto_parse);
        JSONObject rspJson = requestUrl.request_url(constructorParameters, StringEntityBody);

        // 返回值解析
        String plainTextRsp = responseParse.response_parse(rspJson, crypto_parse);

        logger.info("Request end =======");

        return plainTextRsp;
    }

    @RequestMapping("/double-verify")
    public String double_verify(@RequestParam(name = "idCardNumMan", required = true) String idCardNumMan,
                                @RequestParam(name = "idCardNumWoman", required = true) String idCardNumWoman,
                                @RequestParam(name = "nameMan", required = true) String nameMan,
                                @RequestParam(name = "nameWoman", required = true) String nameWoman,
                                @RequestParam(name = "maritalType", required = true) String maritalType) throws Exception {

        logger.info("Request start =======");

        // 初始化配置
        ConstructorParameters constructorParameters = new ConstructorParameters();
        constructorParameters.construct_key(env, ShdataDistributionConf.API_URL, ShdataDistributionConf.DOUBLE_MARITAL_STATUS);
        CryptoParse crypto_parse = new CryptoParse(constructorParameters);

        Map<String, Object> serviceParam = new HashMap<String, Object>();
        // 版本参数
        serviceParam.put("version", constructorParameters.getVersion());
        serviceParam.put("scene", constructorParameters.getScene());
        serviceParam.put("realUser", constructorParameters.getRealUser());

        // 业务参数
        String idManCipherText = crypto_parse.sm4_encrypt(idCardNumMan);
        String idWomanCipherText = crypto_parse.sm4_encrypt(idCardNumWoman);
        serviceParam.put("nameMan", nameMan);
        serviceParam.put("idCardNumMan", idManCipherText);
        serviceParam.put("nameWoman", nameWoman);
        serviceParam.put("idCardNumWoman", idWomanCipherText);
        serviceParam.put("maritalType", maritalType);
        String serviceParamJson = JSONObject.toJSONString(serviceParam);
        logger.info("业务参数:" + serviceParamJson);

        // 请求
        String StringEntityBody = requestUrl.constitute_body(serviceParamJson, crypto_parse);
        JSONObject rspJson = requestUrl.request_url(constructorParameters, StringEntityBody);

        // 返回值解析
        String plainTextRsp = responseParse.response_parse(rspJson, crypto_parse);

        logger.info("Request end =======");

        return plainTextRsp;
    }
}
