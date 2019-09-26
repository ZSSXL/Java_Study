package com.zss.shirodemo.controller;

import com.zss.shirodemo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @date 2019/9/26 10:16
 * @description test controller
 */
@Slf4j
@Controller
@RequestMapping("/page")
public class TestController {

    @PostMapping
    public String loginUser(@RequestBody String username, @RequestBody String password, HttpSession session) {
        System.out.println("username:" + username + " password:" + password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            log.info("登录成功");
            return "/index";
        } catch (Exception e) {
            log.warn("shiro认证异常：", e);
            return "/login";
        }
    }

    @GetMapping("/{page}")
    public String helloHtml(@PathVariable String page) {
        return page;
    }

}
