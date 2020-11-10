package com.zss.mongodbtest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试基类
 *
 * @author ZSS
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    @Before
    public void beforeTest() {
        System.out.println("=============================== 开始测试 ==============================");
    }

    @After
    public void afterTest() {
        System.out.println("=============================== 结束测试 ==============================");
    }
}
