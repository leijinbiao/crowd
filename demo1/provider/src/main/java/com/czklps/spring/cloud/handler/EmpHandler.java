package com.czklps.spring.cloud.handler;

import com.czklps.spring.cloud.entity.Emp;
import com.czklps.spring.cloud.util.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpHandler {

    @RequestMapping("/provider/get/emp/remote")
    public Emp getEmp(){
        return new Emp(1,"jerry", 18888.88);
    }

// 熔断 如果服务器数据出错则走备选方案
    @HystrixCommand(fallbackMethod = "getEmpWithCircuitBreakerBackup")
    @RequestMapping("/provider/get/emp/with/circuit/breaker")
    public ResultEntity<Emp> getEmpWithCircuitBreaker(@RequestParam("signal") String signal) throws InterruptedException {
        if("quick-signal".equals(signal)){
            throw new RuntimeException();
        }

        if("slow-signal".equals(signal)){
            Thread.sleep(5000);
        }

        Emp emp = new Emp(1,"jerry", 2555.55);

        return ResultEntity.successWithData(emp);
    }

    public ResultEntity<Emp> getEmpWithCircuitBreakerBackup(@RequestParam("signal") String signal){
        String message = "方法出现问题，断路了"+signal;
        return ResultEntity.failed(message);
    }

    @RequestMapping("/provider/get/emp/list/remote")
    public List<Emp> getEmpList(@RequestParam("keyword") String keyword){
        List<Emp> list = new ArrayList<>();

        list.add(new Emp(1,"jerry", 2555.55));
        list.add(new Emp(2,"tom", 36666.88));
        list.add(new Emp(3,"jim", 123.12));
        list.add(new Emp(4,"lin", 18888.88));

        return list;
    }

}
