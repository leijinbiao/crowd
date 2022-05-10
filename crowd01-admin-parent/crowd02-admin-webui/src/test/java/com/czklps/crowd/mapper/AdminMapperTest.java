package com.czklps.crowd.mapper;

import com.czklps.crowd.entity.Role;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseException;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.service.api.RoleService;
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

    @Autowired
    private RoleService roleService;

    @Test
    public void insert() throws LoginAcctAlreadyInUseException {
        for (int i = 0; i < 123; i++) {
//            Admin admin = new Admin(null, "jerry" + i, "123456" + i, "杰瑞" + i, "jerry" + i + "@qq.com", null);
//            adminService.saveAdmin(admin);
            Role role = new Role(null,"role"+i);
            roleService.saveRole(role);
        }
//        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "jerry@qq.com", null);
//        adminService.saveAdmin(admin);

    }

}