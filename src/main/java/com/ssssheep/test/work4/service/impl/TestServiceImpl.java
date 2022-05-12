package com.ssssheep.test.work4.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.ssssheep.test.work4.dao.UserDao;
import com.ssssheep.test.work4.entity.User;
import com.ssssheep.test.work4.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/5/12 21:02 星期四
 */
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final RedisTemplate<String, Serializable> serializableRedisTemplate;
    private final UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String test() {
        System.out.println(StpUtil.isLogin());
        int id = 1;
        String prefix = "redis_data";
        User user = new User(1, "yxr", "123");
        userDao.save(user);
        if(Boolean.TRUE.equals(serializableRedisTemplate.hasKey(prefix + id))){
            serializableRedisTemplate.opsForValue().set("hello","world");
        }
        System.out.println(StpUtil.isLogin());
        System.out.println(StpUtil.getLoginId());
        return "ok";
    }
}
