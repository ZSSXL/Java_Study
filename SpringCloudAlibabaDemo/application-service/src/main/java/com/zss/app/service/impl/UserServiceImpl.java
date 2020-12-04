package com.zss.app.service.impl;

import com.zss.app.entity.User;
import com.zss.app.repository.UserRepository;
import com.zss.app.service.UserService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/3 17:13
 * @desc 服务层接口方法实现
 */
@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        User save = userRepository.save(user);
        System.out.println("Save User: " + user.toString());
        return save;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
