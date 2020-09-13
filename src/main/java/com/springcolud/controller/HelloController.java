package com.springcolud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

        @RequestMapping("/hello")
        public String hello(){
         return "this is my first project by springboot";
        }
}
