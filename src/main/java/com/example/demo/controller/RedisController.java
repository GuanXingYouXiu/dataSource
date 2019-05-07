package com.example.demo.controller;

import com.example.demo.utils.TimestampUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @GetMapping("Time")
    public void LocalDate(){

        Long time=System.currentTimeMillis();
        log.info("当前时间戳为》》》"+time);

        LocalDateTime localDateTime=TimestampUtils.timestampToLocalDateTime(time);
        log.info("时间戳转LocalDateTime》》》"+localDateTime);

        LocalDate localDate =TimestampUtils.timestampToLocalDate(time);
        log.info("时间戳转localDate》》》"+localDate);

        Long time1=TimestampUtils.localDateTimeToTimestamp(localDateTime);
        Long time2=TimestampUtils.localDateToTimestamp(localDate);
        log.info("LocalDateTime与LocalDate转时间戳"+time1+">>>"+time2);

        LocalDate localDate1=localDateTime.toLocalDate();
        log.info("LocalDateTime转LocalDate》》》"+localDate1);

        LocalDateTime localDateTime1=TimestampUtils.LocalDateToLocalDateTime(localDate);
        log.info("LocalDate转LocalDateTime》》》"+localDateTime1);
    }
}
