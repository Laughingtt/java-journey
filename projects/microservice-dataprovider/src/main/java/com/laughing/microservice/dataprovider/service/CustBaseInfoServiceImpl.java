package com.laughing.microservice.dataprovider.service;

import com.laughing.microservice.dataprovider.dao.CustBaseInfoDao;
import com.laughing.microservice.dataprovider.entity.CustBaseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhongjie
 * @date 2023/10/23
 */
@Service
public class CustBaseInfoServiceImpl implements CustBaseInfoService {
    @Resource
    private CustBaseInfoDao custBaseInfoDao;

    @Override
    public CustBaseInfo selectById(Long id) {
        return custBaseInfoDao.selectById(id);
    }

}