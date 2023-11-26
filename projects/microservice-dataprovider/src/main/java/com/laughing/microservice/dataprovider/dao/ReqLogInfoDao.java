package com.laughing.microservice.dataprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughing.microservice.dataprovider.entity.ReqLogInfo;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReqLogInfoDao extends BaseMapper<ReqLogInfo> {

    void insertInfo(ReqLogInfo entity);


    List<ReqLogInfo> selectObjs(int times);

}
