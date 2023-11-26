package com.laughing.microservice.dataprovider.controller.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laughing.microservice.dataprovider.dao.ReqLogInfoDao;
import com.laughing.microservice.dataprovider.entity.ReqLogInfo;
import com.laughing.microservice.dataprovider.service.CustBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/service/")
public class ReqLogInfoController {
    @Resource
    private CustBaseInfoService custBaseInfoService;
    @Resource
    private ReqLogInfoDao ReqLogInfoDao;

    @GetMapping("/latencyCheck/{times}")
    public JSONObject latencyCheck(@PathVariable Integer times) {

        JSONObject res_json = new JSONObject();


        if (times > 300) {
            res_json.put("message", "入参时间太长，不能超过300s");
            return res_json;
        }

        JSONArray json_arr = new JSONArray();

        List<ReqLogInfo> objList = ReqLogInfoDao.selectObjs(times);

        for (ReqLogInfo logInfo : objList) {
            JSONObject nestedObject = new JSONObject();

            nestedObject.put("trans_id", logInfo.getTrans_id());
            nestedObject.put("req_start_tsp", logInfo.getReq_start_tsp());
            nestedObject.put("req_end_tsp", logInfo.getReq_end_tsp());
            nestedObject.put("query_start_tsp", logInfo.getQuery_start_tsp());
            nestedObject.put("query_end_tsp", logInfo.getQuery_end_tsp());
            log.info(nestedObject.toJSONString());
            json_arr.add(nestedObject);
        }

        res_json.put("message", json_arr);
        return res_json;
    }

}
