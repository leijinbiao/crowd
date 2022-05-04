package com.czklps.crowd.mapper;

import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class AdminMapperTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void insert() {
        for (int i = 0; i < 123; i++) {
            Admin admin = new Admin(null, "jerry" + i, "123456" + i, "杰瑞" + i, "jerry" + i + "@qq.com", null);
            adminService.saveAdmin(admin);
        }

    }

}