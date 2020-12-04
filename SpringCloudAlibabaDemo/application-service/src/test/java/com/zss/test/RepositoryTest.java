package com.zss.test;

import com.zss.app.entity.User;
import com.zss.app.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/4 9:58
 * @desc 测试
 */
public class RepositoryTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void saveUserTest() {
        User user = new User(1004, "赵六", 26);
        User result = userService.createUser(user);
        System.out.println("Result: " + result.toString());
    }

}
