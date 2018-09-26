package com.jjh.druid;

import com.alibaba.fastjson.JSON;
import com.jjh.druid.models.User;
import com.jjh.druid.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DruidDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

}
