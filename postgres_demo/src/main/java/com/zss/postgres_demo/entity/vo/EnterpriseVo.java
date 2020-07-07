package com.zss.postgres_demo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 17:07
 * @desc enterprise VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseVo {

    private String id;

    private String name;

    private String email;

    private String address;
}
