package com.czklps.crowd.handler;

import com.czklps.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RedisHandler {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("set/redis/key/value/remote")
    public ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {

            ValueOperations<String, String> operations = redisTemplate.opsForValue();

            operations.set(key, value);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {

            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("set/redis/key/value/with/time/out/remote")
    public ResultEntity<String> setRedisKeyValueWithTimeOutRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit) {
        try {

            ValueOperations<String, String> operations = redisTemplate.opsForValue();

            operations.set(key, value, time, timeUnit);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {

            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("get/redis/string/value/by/key/remote")
    public ResultEntity<String> getRedisStringValueByKeyRemote(
            @RequestParam("key") String key) {
        try {

            ValueOperations<String, String> operations = redisTemplate.opsForValue();

            String value = operations.get(key);

            return ResultEntity.successWithData(value);
        } catch (Exception e) {

            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("remove/redis/string/value/by/key/remote")
    public ResultEntity<String> removeRedisStringValueByKeyRemote(
            @RequestParam("key") String key) {
        try {

            redisTemplate.delete(key);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {

            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }
}
