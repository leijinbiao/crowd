package com.czklps.spring.cloud.api;

import com.czklps.spring.cloud.entity.Emp;
import com.czklps.spring.cloud.factory.MyFallBackFactory;
import com.czklps.spring.cloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
// value 去eureka通过微服务名称找到对应的接口
// fallbackFactory 降级 服务器迟迟没有反应则走自己的备选方案
@FeignClient(value="provider", fallbackFactory = MyFallBackFactory.class)
public interface EmployeeRemoteService {
    @RequestMapping("/provider/get/emp/remote")
    public Emp getEmp();

    @RequestMapping("/provider/get/emp/list/remote")
    public List<Emp> getEmpList(@RequestParam("keyword") String keyword);

    @RequestMapping("/provider/get/emp/with/circuit/breaker")
    public ResultEntity<Emp> getEmpWithCircuitBreaker(@RequestParam("signal") String signal);
}