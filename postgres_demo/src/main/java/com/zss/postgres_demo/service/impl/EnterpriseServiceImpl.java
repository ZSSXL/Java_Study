package com.zss.postgres_demo.service.impl;

import com.zss.postgres_demo.entity.Enterprise;
import com.zss.postgres_demo.entity.vo.EnterpriseVo;
import com.zss.postgres_demo.repostitory.EnterpriseRepository;
import com.zss.postgres_demo.service.EnterpriseService;
import com.zss.postgres_demo.util.GeneralConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 13:58
 * @desc enterprise 服务层接口方法实现
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final GeneralConverter generalConverter;

    @Autowired
    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository, GeneralConverter generalConverter) {
        this.enterpriseRepository = enterpriseRepository;
        this.generalConverter = generalConverter;
    }

    @Override
    public Boolean addEnterprise(EnterpriseVo enterpriseVo) {
        Enterprise enp = generalConverter.converter(enterpriseVo, Enterprise.class);
        Enterprise save = enterpriseRepository.save(enp);
        System.out.println("Save EnterpriseVO : [ " + enterpriseVo + " ]");
        System.out.println("Save Enterprise : [ " + save + " ]");
        return true;
    }

    @Override
    public Boolean addManyEnterprises(List<EnterpriseVo> enterpriseVoList) {
        return null;
    }

    @Override
    public List<EnterpriseVo> selectAllEnterprise() {
        List<Enterprise> all = enterpriseRepository.findAll();
        return generalConverter.converter(all, EnterpriseVo.class);
    }
}
