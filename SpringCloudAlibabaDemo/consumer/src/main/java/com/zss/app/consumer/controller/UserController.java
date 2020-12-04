package com.zss.app.consumer.controller;

import com.zss.app.entity.User;
import com.zss.app.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/4 10:13
 * @desc User 控制器
 */
@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(user -> System.out.println("User: " + user.toString()));
        return allUsers;
    }

}
