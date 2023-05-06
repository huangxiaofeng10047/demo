package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hxf
 * @date 2023年05月06日 9:33
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    @GetMapping("/hello2")
    public R getInfo2(){
        log.debug("debug ****************************");
        log.info("info ****************************");
        log.warn("warn ****************************");
        log.error("error ****************************");
        User user = User.builder()
                .userName("111")
                .userPwd("222")
                .createTime(new Date()).build();
        Map<Object, Object> map = new HashMap<>();
        return R.ok(user);
    }
    @GetMapping("/hello3")
    public Object getInfo3(){
        log.debug("debug ****************************");
        log.info("info ****************************");
        log.warn("warn ****************************");
        log.error("error ****************************");
        User user = User.builder()
                .userName("111")
                .userPwd("222")
                .createTime(new Date()).build();
        Map<Object, Object> map = new HashMap<>();
        map.put("user",user);
        return map;
    }

}
