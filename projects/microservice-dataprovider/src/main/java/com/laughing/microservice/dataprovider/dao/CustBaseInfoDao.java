package com.laughing.microservice.dataprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughing.microservice.dataprovider.entity.CustBaseInfo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @description: 客户基础信息
 * @author: XXM
 * @date: 2023/7/12 15:29
 */
@Repository
public interface CustBaseInfoDao extends BaseMapper<CustBaseInfo> {
    @Override
    List<CustBaseInfo> selectBatchIds(Collection<? extends Serializable> idList);
}
