package com.zss.jwt.entity;

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
 * @date 2020/7/7 10:50
 * @desc 管理员 实体
 */
@Data
@Builder
@Entity(name = "manager")
@AllArgsConstructor
@NoArgsConstructor
@Table(appliesTo = "manager", comment = "管理员")
public class Manager implements Serializable {

    /**
     * 管理员Id
     */
    @Id
    private String managerId;

    /**
     * 管理员名称
     */
    private String managerName;

    /**
     * 管理员密码
     */
    private String managerPassword;
}
