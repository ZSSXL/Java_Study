package com.zss.mongodbtest.service;

import com.zss.mongodbtest.model.entity.Enterprise;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:31
 * @desc Enterprise 服务层接口
 */
public interface EnterpriseService {

    /**
     * 添加新企业
     *
     * @param enp 企业实体
     * @return Boolean
     */
    Boolean insertNewEnterprise(Enterprise enp);

    /**
     * 批量添加企业
     *
     * @param enterpriseList 多个企业
     * @return Boolean
     */
    Boolean addManyEnterprise(List<Enterprise> enterpriseList);

    /**
     * 获取所有企业
     *
     * @return List<Enterprise>
     */
    List<Enterprise> selectAll();
}
