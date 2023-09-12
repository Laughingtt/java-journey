package com.shdata.crypto_parse_bridge.cryptoparsebridge.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController  //等价于上面两个
@RequestMapping("/api")
public class Demo {


    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!--quick";
    }

    @RequestMapping("/hi")
    public String hi2() {
        return "hello hi2 World!--quick";
    }

    @GetMapping("/user")
    public String getUser(@RequestParam(name = "id") Long userId,
                          @RequestParam(name = "name", required = false) String userName)
    {
        // 模拟根据用户ID查询用户信息
        String userDetails = "User details for ID: " + userId;
        if (userName != null) {
            userDetails += ", Name: " + userName;
        }
        return userDetails;
    }

    @Async
    @GetMapping("/api/books/async")
    public CompletableFuture<String> getAsyncBooks() {
        // 模拟耗时操作
        try {
            System.out.println("sleep 2000");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("List of all books (async)");
    }
}