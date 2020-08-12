package com.zss.lock;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/12 14:25
 * @desc Spring测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    @Before
    public void before() {
        System.out.println("=========================== 测试开始 ===========================");
    }

    @After
    public void after() {
        System.out.println("=========================== 测试开始 ===========================");
    }
}
