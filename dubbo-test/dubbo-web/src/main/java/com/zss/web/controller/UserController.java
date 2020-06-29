package com.zss.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.api.entity.User;
import com.zss.api.service.UserService;
import com.zss.base.response.ServerResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZSS
 * @company 上海数慧系统技术有限公司
 * @Dept 数据中心
 * @email zhoushs@dist.com.cn
 * @date 2020/6/28 17:00
 * @Desc
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "1.0.0", interfaceClass = UserService.class)
    private UserService userService;

    /**
     * sayHello
     *
     * @param content 内容
     * @return String
     */
    @GetMapping("/{content}")
    public ServerResponse<String> sayHello(@PathVariable("content") String content) {
        String back = userService.sayHello(content);
        System.out.println("content : [" + back + "]");
        return ServerResponse.createBySuccess(back);
    }

    /**
     * getUser
     *
     * @param user user
     * @return User
     */
    @PostMapping
    public ServerResponse<User> getUser(@RequestBody User user) {
        User result = userService.helloUser(user);
        System.out.println("result : [" + result + "]");
        return ServerResponse.createBySuccess(result);
    }
}
