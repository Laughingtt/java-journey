package com.laughing.microservice.dataprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.Instant;


@Data
@TableName(value = "req_log_info")
public class ReqLogInfo {


    @TableId(type = IdType.AUTO)
    Integer id;

    String trans_id;
    String req_path;
    Instant req_start_tsp;
    Instant req_end_tsp;
    Instant query_start_tsp;
    Instant query_end_tsp;

    String req_time;
    String info;
}
