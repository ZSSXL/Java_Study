package com.zss.postgres_demo.service;

import com.zss.postgres_demo.entity.vo.EnterpriseVo;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 13:57
 * @desc enterprise 服务层接口
 */
public interface EnterpriseService {

    /**
     * 添加新企业
     *
     * @param enterpriseVo 企业VO
     * @return Boolean
     */
    Boolean addEnterprise(EnterpriseVo enterpriseVo);

    /**
     * 批量添加企业
     *
     * @param enterpriseVoList 多个企业VO
     * @return Boolean
     */
    Boolean addManyEnterprises(List<EnterpriseVo> enterpriseVoList);

    /**
     * 获取所有的企业信息
     *
     * @return List<EnterpriseVo>
     */
    List<EnterpriseVo> selectAllEnterprise();
}
