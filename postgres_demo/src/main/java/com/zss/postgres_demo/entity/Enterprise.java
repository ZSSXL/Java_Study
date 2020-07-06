package com.zss.postgres_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 9:28
 * @desc 企业 实体
 */
@Data
@Builder
@Entity(name = "enterprise")
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise implements Serializable {

    /**
     * 企业Id
     */
    @Id
    private String enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 企业邮箱
     */
    private String enterpriseEmail;

    /**
     * 企业地址
     */
    private String enterpriseAddress;

}
