package com.zss.mvcframework.controller;

import com.zss.mvcframework.annotation.ZssAutowired;
import com.zss.mvcframework.annotation.ZssController;
import com.zss.mvcframework.annotation.ZssRequestMapping;
import com.zss.mvcframework.annotation.ZssRequestParam;

/**
 * @author ZSS
 * @date 2019/10/26 16:29
 * @description controller 测试
 */
@ZssController
public class DemoController {

    @ZssAutowired
    private String test = "test";

    @ZssRequestMapping
    public void test(@ZssRequestParam String username){
        System.out.println(username);
    }


}
