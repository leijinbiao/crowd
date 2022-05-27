package com.czklps.spring.cloud.handler;

import com.czklps.spring.cloud.api.EmployeeRemoteService;
import com.czklps.spring.cloud.entity.Emp;
import com.czklps.spring.cloud.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignHuManResourceHandler {

    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/consumer/feign/fallback")
    public ResultEntity<Emp> testFallBack(@RequestParam("signal") String signal) {
        return employeeRemoteService.getEmpWithCircuitBreaker(signal);
    }

    @RequestMapping("/consumer/feign/get/employee")
    public Emp getEmpRemote() {
        return employeeRemoteService.getEmp();
    }

    @RequestMapping("/consumer/feign/get/employee/list")
    public List<Emp> getEmpListRemote() {
        return employeeRemoteService.getEmpList("123");
    }

}
