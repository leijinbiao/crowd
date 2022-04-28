package com.czklps.crowd.util;


import org.junit.Test;

public class ResultEntityTest {

    @Test
    public void testToString() {
        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "jerry@qq.com", null);
        System.out.println(ResultEntity.successWithData(admin));
    }
}