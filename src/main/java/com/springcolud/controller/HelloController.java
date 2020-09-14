package com.springcolud.controller;

import com.springcolud.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private RedisService redisService;

    @RequestMapping("/hello")
     public String hello(){
        log.info("21321321321");
        redisService.set("yin","penghua");
        String valu = redisService.get("yin");
        System.out.println(valu);
       return "this is my first project by springboot";
     }
}
