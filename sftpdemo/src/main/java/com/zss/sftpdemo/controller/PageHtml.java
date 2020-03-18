package com.zss.sftpdemo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ZSS
 * @date 2019/8/16 11:00
 * @description 页面条状controller
 */
@Controller
public class PageHtml {

    /**
     * 页面跳转
     *
     * @param page page
     * @return String
     */
    @GetMapping("/{page}")
    public String helloHtml(@PathVariable String page) {
        if(StringUtils.equals(page, "favicon.ico")){
            return "file";
        } else {
            return page;
        }
    }

}
