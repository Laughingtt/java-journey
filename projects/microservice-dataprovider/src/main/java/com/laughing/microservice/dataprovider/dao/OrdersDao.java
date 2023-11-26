package com.laughing.microservice.dataprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughing.microservice.dataprovider.entity.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: CustomerID
 * @author: XXM
 * @date: 2023/7/12 15:29
 */
@Repository
public interface OrdersDao extends BaseMapper<Orders> {

    List<Orders> selectById(Integer CustomerID);

//    List<VerifyTripartiteAgreement> selectAll();
}
