package com.czklps.ljb.demo;

import com.czklps.ljb.demo.mybatis.entity.User;
import com.czklps.ljb.demo.mybatis.entity.UserExample;
import com.czklps.ljb.demo.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Objects;

@SpringBootTest
class Demo1ApplicationTests {
    @Value("${com.czklps.best.wishes}")
    private String wishes;

    Logger logger = LoggerFactory.getLogger(Demo1ApplicationTests.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testStringRedisTemplate(){
        ListOperations<String, String> operations = stringRedisTemplate.opsForList();
        operations.leftPush("fruit","apple");
        operations.leftPush("fruit","banana");
        operations.leftPush("fruit","pears");
        operations.leftPush("fruit","orange");
    }

//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
    
//    @Test
//    void testMyStringRedisTemple(){
//        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//
//        operations.set("stringkey","stringvalue");
//    }

//    @Autowired
//    private RedisTemplate<Object,Object> redisTemplate;
//
//    @Test
//    void testRedisTemplate(){
//        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
//        Object key = "key123";
//        Object value = "value123";
//
//        operations.set(key, value);
//    }

    @Test
    void contextLoads() {
        List<User> list = userMapper.selectByExample(new UserExample());
        for (User user : list) {
            logger.info(user.toString());
        }
        logger.info(wishes);
    }

}
