package com.giser.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author giserDev
 * @description
 * @date 2022-09-14 22:39:58
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello, security!";
    }

}
