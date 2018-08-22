package com.forezp.eurekaclient.controller;

import com.forezp.eurekaclient.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title RedisController
 * @Description ${DESCRIPTION}
 * @Author Yao.Zhang
 * @date: 2018/7/9
 */

@RestController
public class RedisController {


    @Autowired
    RedisUtil redisUtil;


    @RequestMapping("/setKey")
    public String home(@RequestParam String key,@RequestParam String value) {
        redisUtil.setKey(key,value);
        return "已成功保存";
    }

    @RequestMapping("/getKey")
    public String home(@RequestParam String key) {
        return redisUtil.getValue(key);
    }
}
