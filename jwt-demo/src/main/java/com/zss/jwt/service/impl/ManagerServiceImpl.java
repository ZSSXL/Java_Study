package com.zss.jwt.service.impl;

import com.zss.jwt.entity.Manager;
import com.zss.jwt.repository.ManagerRepository;
import com.zss.jwt.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/7 11:01
 * @desc manager 服务层接口实现
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Boolean addManagerService(Manager manager) {
        Manager save = managerRepository.save(manager);
        System.out.println("Save Result : [" + save + "]");
        return true;
    }

    @Override
    public Manager selectManagerByName(String username) {
        return managerRepository.findByManagerName(username);
    }

    @Override
    public Manager login(String username, String password) {
        Manager managerLogin = managerRepository.findByManagerNameAndManagerPassword(username, password);
        System.out.println("Login Result : [" + managerLogin + "]");
        return managerLogin;
    }
}
