package com.zss.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.api.entity.User;
import com.zss.api.service.UserService;
import com.zss.repository.UserRepository;

import javax.annotation.Resource;

/**
 * @author ZSS
 * @company 上海数慧系统技术有限公司
 * @Dept 数据中心
 * @email zhoushs@dist.com.cn
 * @date 2020/6/28 15:53
 * @Desc user服务层接口方法的实现
 */
@Service(version = "1.0.0", interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public String sayHello(String content) {
        return "Hello, welcome to use dubbo + [ " + content + " ]";
    }

    @Override
    public User helloUser(User user) {
        System.out.println("[" + user + "]");
        User save = userRepository.save(user);
        System.out.println("save : [" + save + "]");
        return user;
    }
}
