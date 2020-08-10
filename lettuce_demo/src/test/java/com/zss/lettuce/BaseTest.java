package com.zss.lettuce;

import org.junit.After;
import org.junit.Before;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 17:30
 * @desc 测试基类
 */
public class BaseTest {

    @Before
    public void before() {
        System.out.println("========================== 测试开始 ==========================");
    }


    @After
    public void after() {
        System.out.println("========================== 测试结束 ==========================");
    }
}
