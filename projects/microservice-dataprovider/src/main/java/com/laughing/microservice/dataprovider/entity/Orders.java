package com.laughing.microservice.dataprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 订单表
 * @author: TJ
 * @date: 2023/7/11 17:25
 */
@Data
@TableName(value = "Orders")
public class Orders {

    // 证件号
    @TableId(type = IdType.AUTO)
    Integer OrderID;

    Integer CustomerID;
    Integer ProductID;
    Integer Quantity;
    Double Price;

    // 数据更新时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date OrderDate;

    String OrderName;

}
