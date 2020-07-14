package com.zss.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ZSS
 * @company 上海数慧系统技术有限公司
 * @Dept 数据中心
 * @email zhoushs@dist.com.cn
 * @date 2020/6/28 15:29
 * @Desc user实体类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "test_user")
@Table(appliesTo = "test_user", comment = "测试")
public class User implements Serializable {

    @Id
    @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String username;

    @Column(columnDefinition = "varchar(100)")
    private String password;

    private String email;

    private String phone;

    private String address;

    private String age;
}
