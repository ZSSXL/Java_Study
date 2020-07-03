package com.zss.mongodbtest.service.impl;

import com.zss.mongodbtest.model.entity.Enterprise;
import com.zss.mongodbtest.repository.EnterpriseRepository;
import com.zss.mongodbtest.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:31
 * @desc Enterprise 服务层方法实现
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Boolean insertNewEnterprise(Enterprise enp) {
        Enterprise insert = enterpriseRepository.insert(enp);
        System.out.println("Insert : [" + insert + "]");
        return true;
    }

    @Override
    public Boolean addManyEnterprise(List<Enterprise> enterpriseList) {
        List<Enterprise> insert = enterpriseRepository.insert(enterpriseList);
        for (Enterprise enp : insert) {
            System.out.println("Enterprise : [" + enp + "]");
        }
        return true;
    }

    @Override
    public List<Enterprise> selectAll() {
        return enterpriseRepository.findAll();
    }
}
