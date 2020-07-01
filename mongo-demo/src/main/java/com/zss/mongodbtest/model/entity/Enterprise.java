package com.zss.mongodbtest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:02
 * @desc 企业信息实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {

    private String enterpriseId;

    private String enterpriseName;

    private String enterpriseEmail;

    private String enterpriseAddress;

    private String enterpriseWeb;
}
