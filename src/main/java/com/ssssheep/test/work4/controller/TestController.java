package com.ssssheep.test.work4.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.ssssheep.test.work4.entity.User;
import com.ssssheep.test.work4.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/5/12 20:41 星期四
 */
@RequestMapping(value = "/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final RedisTemplate<String, Serializable> redisTemplate;
    @GetMapping("/login")
    public String doLogin(String name, String password){
        if("aaa".equals(name) && "123".equals(password)){
            StpUtil.login(123);
            User user = new User(1,"yxr","123456");
            redisTemplate.opsForValue().set("redis_data" + user.getId(), user);
            return StpUtil.getTokenInfo().getTokenValue();
        }else{
            return "invalid password";
        }
    }

    @PutMapping("/test")
    @SaCheckRole(value = {"super_admin"},mode = SaMode.OR)
    public String test(){
        return testService.test();
    }
}
