package com.zss.mongodbtest;

import com.zss.mongodbtest.model.entity.Enterprise;
import com.zss.mongodbtest.service.EnterpriseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:34
 * @desc Enterprise 测试
 */
public class EnterpriseTest extends BaseTest {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 添加新的企业 - 测试
     */
    @Test
    public void insertNewEnterpriseTest() {
        Enterprise build = Enterprise.builder()
                .enterpriseId("2003")
                .enterpriseName("jingxi")
                .enterpriseEmail("jingxi@123.com")
                .enterpriseAddress("北京")
                .enterpriseWeb("www.jingxi.com")
                .build();
        Boolean result = enterpriseService.insertNewEnterprise(build);
        System.out.println("Insert result : " + result);
    }

    /**
     * 查询所有企业 - 测试
     */
    @Test
    public void selectAll() {
        List<Enterprise> enterpriseList = enterpriseService.selectAll();
        for (Enterprise enp : enterpriseList) {
            System.out.println("Enterprise : [" + enp + "]");
        }
    }
}
