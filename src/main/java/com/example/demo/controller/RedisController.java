package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("redisMethod")
    public void redisMethod(){
        log.info(">>>>>>>>>>>>  come in");
        //在redis中缓存数据
        redisTemplate.opsForValue().set("RedisSet","Welcome To Redis!");
        //调用redis中的数据
        String redisGet=redisTemplate.opsForValue().get("RedisSet");
        log.info("redis缓存成功了》》》》》》"+redisGet);
    }
}
