package com.laughing.microservice.dataprovider.controller;
import com.laughing.microservice.dataprovider.dao.OrdersDao;
import com.laughing.microservice.dataprovider.entity.Orders;
import com.laughing.microservice.dataprovider.in.OrdersIn;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @date 2023/10/23
 */
@Slf4j
@RestController
@RequestMapping("/demo/")
public class OrdersController extends BaseController {

    @Resource
    private OrdersDao OrdersDao;

    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    @PostMapping({"/get_custom_v1"})
    public JSONObject get_custom_v1(@RequestBody @Validated OrdersIn OrdersIn, HttpServletRequest request) throws Exception {

        log.info("请求业务流参数：url[{}], body[{}]", "OrdersIn", OrdersIn.toString());
        return ReqExecutor(OrdersIn, request);
    }

    @Override
    public JSONObject getQueryResult(Object object) {

        OrdersIn OrdersIn = (OrdersIn) object;

        Integer customerID = Integer.valueOf(OrdersIn.getCustomerId());

        List<Orders> orders = OrdersDao.selectById(customerID);

        log.info("query result is {}", orders);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", Arrays.toString(orders.toArray()));

        return jsonObject;
    }

}
