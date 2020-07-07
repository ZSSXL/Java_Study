package com.zss.postgres_demo;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 14:05
 * @desc 测试基类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    @Before
    public void beforeTest() {
        System.out.println("======================================== 开始测试 =======================================");
    }

    @After
    public void afterTest() {
        System.out.println("======================================== 结束测试 =======================================");
    }

}
