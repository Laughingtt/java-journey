package com.laughing.microservice.dataprovider.service;

import com.laughing.microservice.dataprovider.entity.CustBaseInfo;

/**
 * @author zhongjie
 * @date 2023/10/23
 */
public interface CustBaseInfoService {
    CustBaseInfo selectById(Long id);
}
