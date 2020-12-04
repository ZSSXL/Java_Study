package com.zss.test;

import com.zss.app.ServiceApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/4 9:56
 * @desc 测试基类
 */
@SpringBootTest(classes= ServiceApplication.class)
@RunWith(SpringRunner.class)
public class BaseTest {

    @Before
    public void before() {
        System.out.println("========================= 测试开始 =========================");
    }

    @After
    public void after() {
        System.out.println("========================= 测试结束 =========================");
    }
}
