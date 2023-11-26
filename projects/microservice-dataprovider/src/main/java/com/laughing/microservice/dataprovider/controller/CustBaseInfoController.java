package com.laughing.microservice.dataprovider.controller;

import com.laughing.microservice.dataprovider.dao.TransactionsDao;
import com.laughing.microservice.dataprovider.service.CustBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhongjie
 * @date 2023/10/23
 */
@Slf4j
@RestController
@RequestMapping("/cust/")
public class CustBaseInfoController {
    @Resource
    private CustBaseInfoService custBaseInfoService;
    @Resource
    private TransactionsDao transactionsDao;

    @GetMapping("/selectById/{id}")
    public Integer selectById(@PathVariable Integer id) {
        return transactionsDao.selectAmount(id);
    }

    @GetMapping("/select/{name}")
    public String selectname(@PathVariable String name) {
        log.info("社保接口：入参={},时间={}", name, System.currentTimeMillis());
        return "hello world";
    }

}
