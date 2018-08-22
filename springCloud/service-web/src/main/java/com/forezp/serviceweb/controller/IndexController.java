package com.forezp.serviceweb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title IndexController
 * @Description ${DESCRIPTION}
 * @Author Yao.Zhang
 * @date: 2018/6/26
 */
@RestController
public class IndexController {

    @Value("${server.port}")
    String port;
    @RequestMapping("/hello")
    public String home(@RequestParam String name) {
        System.out.println("name:"+name);
        return "index.html";
    }
}
