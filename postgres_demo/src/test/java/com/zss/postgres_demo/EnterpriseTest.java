package com.zss.postgres_demo;

import com.zss.postgres_demo.entity.vo.EnterpriseVo;
import com.zss.postgres_demo.service.EnterpriseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 17:31
 * @desc enterprise  - 测试
 */
public class EnterpriseTest extends BaseTest {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 添加企业 - 测试
     */
    @Test
    public void addEnterpriseTest() {
        EnterpriseVo build = EnterpriseVo.builder()
                .id("2002")
                .name("penguin")
                .address("shenzhen")
                .email("www.penguin.com")
                .build();
        Boolean result = enterpriseService.addEnterprise(build);
        System.out.println("Save Result : " + result);
    }

    /**
     * 查询所有 - 测试
     */
    @Test
    public void selectAllEnterprise() {
        List<EnterpriseVo> enterpriseVoList = enterpriseService.selectAllEnterprise();
        for (EnterpriseVo ev : enterpriseVoList) {
            System.out.println("Select Result : [" + ev + "]");
        }
    }
}
