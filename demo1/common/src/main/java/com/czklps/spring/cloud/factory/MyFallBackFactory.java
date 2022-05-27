package com.czklps.spring.cloud.factory;

import com.czklps.spring.cloud.api.EmployeeRemoteService;
import com.czklps.spring.cloud.entity.Emp;
import com.czklps.spring.cloud.util.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyFallBackFactory implements FallbackFactory {
    @Override
    public EmployeeRemoteService create(Throwable cause) {
        return new EmployeeRemoteService() {
            @Override
            public Emp getEmp() {
                return null;
            }

            @Override
            public List<Emp> getEmpList(String keyword) {
                return null;
            }

            @Override
            public ResultEntity<Emp> getEmpWithCircuitBreaker(String signal) {
                return ResultEntity.failed(cause.getMessage());
            }
        };
    }
}
