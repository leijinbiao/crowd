package com.czklps.spring.cloud.handler;

import com.czklps.spring.cloud.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HuManResourceHandler {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/ribbon/get/employee")
    public Emp getEmpRemote() {

        String host = "http://provider";

        String url = "/provider/get/employee/remote";

        return restTemplate.getForObject(host + url, Emp.class);
    }
}
