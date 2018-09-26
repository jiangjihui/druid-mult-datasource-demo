package com.jjh.druid.controllers;

import com.alibaba.fastjson.JSON;
import com.jjh.druid.models.User;
import com.jjh.druid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public Object add() {
        String json = "{name:'jjh'}";
        User user = JSON.parseObject(json, User.class);
        System.out.println(user);
        userService.save(user);
        return user;
    }

}
