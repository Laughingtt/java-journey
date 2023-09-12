package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api2")
public class Hi {

    @RequestMapping("/hi")
    public String hello() {
        return "hi World!--quick";
    }
}
