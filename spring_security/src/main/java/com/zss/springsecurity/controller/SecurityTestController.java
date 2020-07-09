package com.zss.springsecurity.controller;

import com.zss.springsecurity.common.response.ServerResponse;
import org.apache.catalina.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/9 14:10
 * @desc Spring Security 测试控制器
 */
@RestController
@RequestMapping("/security")
public class SecurityTestController {

    @GetMapping
    public ServerResponse<String> securityTest() {
        System.out.println("Security Test");
        return ServerResponse.createByErrorMessage("Hello, Master!!!");
    }
}
