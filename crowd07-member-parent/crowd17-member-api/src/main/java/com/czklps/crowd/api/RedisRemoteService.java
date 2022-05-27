package com.czklps.crowd.api;

import com.czklps.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

@FeignClient("crowd-redis-provider")
public interface RedisRemoteService {
    @RequestMapping("set/redis/key/value/remote")
    public ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping("set/redis/key/value/with/time/out/remote")
    public ResultEntity<String> setRedisKeyValueWithTimeOutRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit);

    @RequestMapping("get/redis/string/value/by/key/remote")
    public ResultEntity<String> getRedisStringValueByKeyRemote(
            @RequestParam("key") String key);

    @RequestMapping("remove/redis/string/value/by/key/remote")
    public ResultEntity<String> removeRedisStringValueByKeyRemote(
            @RequestParam("key") String key);
}
