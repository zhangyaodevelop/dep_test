package com.forezp.ribbon.controler;

import com.forezp.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title HelloControler
 * @Description ${DESCRIPTION}
 * @Author Yao.Zhang
 * @date: 2018/6/1
 */

@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name+"-byRibbon");
    }
}
