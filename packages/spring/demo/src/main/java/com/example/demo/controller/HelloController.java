package com.example.demo.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController  //等价于上面两个
@RequestMapping("/api")
public class HelloController {


    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!--quick";
    }

    @RequestMapping("/hi")
    public String hi2() {
        return "hello hi2 World!--quick";
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