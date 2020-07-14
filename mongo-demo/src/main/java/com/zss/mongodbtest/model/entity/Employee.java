package com.zss.mongodbtest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:02
 * @desc 员工信息实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "emp_employee")
public class Employee implements Serializable {

    private String empId;

    private String empName;

    private String empEmail;

    private String empPhone;

}
