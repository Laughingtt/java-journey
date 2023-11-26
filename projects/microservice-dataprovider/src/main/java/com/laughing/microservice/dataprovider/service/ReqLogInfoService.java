package com.laughing.microservice.dataprovider.service;

import com.laughing.microservice.dataprovider.dao.ReqLogInfoDao;
import com.laughing.microservice.dataprovider.entity.ReqLogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ReqLogInfoService {

    @Autowired
    private ReqLogInfoDao reqLogInfoDao;

    @Async
    public CompletableFuture<Void> insertInfoAsync(ReqLogInfo reqLogInfo) {

        CompletableFuture<Void> future = new CompletableFuture<>();
        try {
            reqLogInfoDao.insert(reqLogInfo);
            future.complete(null); // 表示异步操作成功完成
        } catch (Exception e) {
            future.completeExceptionally(e); // 表示异步操作发生异常
        }
        return future;
    }
}
