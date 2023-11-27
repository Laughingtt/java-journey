package com.laughing.microservice.dataprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 交易订单练习
 * @author: XXM
 * @date: 2023/7/11 17:25
 */
@Data
@TableName(value = "Transactions")
public class Transactions {


    @TableId(type = IdType.AUTO)
    Integer id;
    Integer amount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date trans_date;

}
