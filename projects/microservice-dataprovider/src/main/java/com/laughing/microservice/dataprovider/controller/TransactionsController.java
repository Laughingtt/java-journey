package com.laughing.microservice.dataprovider.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laughing.microservice.common.utils.LogService;
import com.laughing.microservice.crypto.utils.CryptoParseUtil;
import com.laughing.microservice.dataprovider.conf.CryptoConfig;
import com.laughing.microservice.dataprovider.conf.SetConf;
import com.laughing.microservice.dataprovider.dao.ReqLogInfoDao;
import com.laughing.microservice.dataprovider.dao.TransactionsDao;
import com.laughing.microservice.dataprovider.entity.Transactions;
import com.laughing.microservice.dataprovider.in.SingleMaritalVerifyIn;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2023/10/23
 * 主要用于接口测试
 */
@Slf4j
@RestController
@RequestMapping("/test/")
public class TransactionsController extends BaseController {
    @Resource
    private TransactionsDao TransactionsDao;

    @Resource
    private ReqLogInfoDao ReqLogInfoDao;

    @Resource
    private SetConf SetConf;

    private final LogService logService;


    private static final Map<String, String> configDictionary = new HashMap<>();

    public TransactionsController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("/hello_world")
    public String selectById(HttpServletRequest request) {
        System.out.println(SetConf.getServiceKey());
        System.out.println(SetConf.getSm4Key());
        log.info("时间={}", System.currentTimeMillis());

        return "Hello World";
    }

    @GetMapping("/encode/{id}")
    public JSONObject encode(@PathVariable String id) throws Exception {
        JSONObject resjsonObject = new JSONObject(true);
        log.info("参数{}", id);
        log.info("sm4 key {}", env.getProperty(CryptoConfig.sm4Key));

        String enc = CryptoParseUtil.sm4Encrypt(id, env.getProperty(CryptoConfig.sm4Key));
        log.info("时间={}", System.currentTimeMillis());
        resjsonObject.put("msg", enc);
        return resjsonObject;
    }

    @GetMapping("/decode/{id}")
    public JSONObject decode(@PathVariable String id) throws Exception {
        JSONObject resjsonObject = new JSONObject(true);
        log.info("参数{}", id);
        log.info("sm4 key {}", env.getProperty(CryptoConfig.sm4Key));

        String dec = CryptoParseUtil.sm4Decrypt(id, env.getProperty(CryptoConfig.sm4Key));
        log.info("时间={}", System.currentTimeMillis());
        resjsonObject.put("msg", dec);

        return resjsonObject;
    }


    @GetMapping("/selectById/{id}")
    public Integer selectById(@PathVariable Integer id) {

        this.init_key(env);

        log.info("社保接口：入参={},时间={}", id, System.currentTimeMillis());

        Integer a = TransactionsDao.selectAmount(id);

        log.info(String.valueOf(a));

        return 111;
    }

    @GetMapping("/selectByIdAll/{id}")
    public JSONObject selectByIdAll(@PathVariable Integer id) {

        this.init_key(env);

        log.info("社保接口：入参={},时间={}", id, System.currentTimeMillis());

        List<Transactions> obj = TransactionsDao.selectById(id);

        log.info(obj.toString());


        JSONObject res_json = new JSONObject();
        if (!obj.isEmpty()) {
            Integer _id = ((Transactions) obj.toArray()[0]).getId();
            Integer _amount = ((Transactions) obj.toArray()[0]).getAmount();
            Date _trans_date = ((Transactions) obj.toArray()[0]).getTrans_date();
            res_json.put("id", _id);
            res_json.put("amount", _amount);
            res_json.put("trans_date", _trans_date);
        }

        return res_json;
    }

    @GetMapping("/selectAll")
    public JSONObject selectAll() {

        this.init_key(env);

        log.info("社保接口：入参={},时间={}", System.currentTimeMillis());

        List<Transactions> obj = TransactionsDao.selectAll();

        log.info(obj.toString());
        JSONObject res_json = new JSONObject();

        if (obj.isEmpty()) {
            res_json.put("mssage", "返回参数为空");
            return res_json;
        }

        JSONArray json_arr = new JSONArray();

        for (int i = 0; i < obj.size(); i++) {
            JSONObject nestedObject = new JSONObject();

            Integer _id = ((Transactions) obj.toArray()[i]).getId();
            Integer _amount = ((Transactions) obj.toArray()[i]).getAmount();
            Date _trans_date = ((Transactions) obj.toArray()[i]).getTrans_date();

            nestedObject.put("id", _id);
            nestedObject.put("amount", _amount);
            nestedObject.put("trans_date", _trans_date);
            log.info(nestedObject.toJSONString());
            json_arr.add(nestedObject);
        }

        res_json.put("message", json_arr);

        return res_json;
    }

    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    @PostMapping({"/post_v1"})
    public JSONObject singleMarital(@RequestBody @Validated SingleMaritalVerifyIn singleMaritalVerifyIn, HttpServletRequest request) throws Exception {

        log.info("请求业务流参数：url[{}], body[{}]", "marriage_single_v1", singleMaritalVerifyIn.toString());

        return ReqExecutor(singleMaritalVerifyIn, request);
    }


    @Override
    public JSONObject getQueryResult(Object object) {
        JSONObject jsonObject = new JSONObject();

        SingleMaritalVerifyIn objectR = (SingleMaritalVerifyIn) object;
        // 业务参数
        jsonObject.put("name", objectR.getName());

        return jsonObject;


    }
}