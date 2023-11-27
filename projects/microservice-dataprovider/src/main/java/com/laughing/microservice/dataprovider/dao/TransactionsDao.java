package com.laughing.microservice.dataprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughing.microservice.dataprovider.entity.Transactions;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @description: 客户基础信息
 * @author: XXM
 * @date: 2023/7/12 15:29
 */
@Repository
public interface TransactionsDao extends BaseMapper<Transactions> {
    Integer selectAmount(Integer id);

    List<Transactions> selectById(Integer id);

    List<Transactions> selectAll();
}
