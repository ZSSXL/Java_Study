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
 * @date 2020/7/6 13:11
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee")
@Table(appliesTo = "employee", comment = "员工表")
public class Employee implements Serializable {

    /**
     * 员工Id
     */
    @Id
    private String employeeId;

    /**
     * 员工名称
     */
    private String employeeName;

    /**
     * 员工电话
     */
    private String employeePhone;

    /**
     * 员工年龄
     */
    private String employeeAge;
}
