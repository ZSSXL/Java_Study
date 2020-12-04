package com.zss.app.controller;

import com.zss.app.entity.User;
import com.zss.app.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/4 11:07
 * @desc
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(item -> System.out.println("User: " + item));
        return allUsers;
    }
}
