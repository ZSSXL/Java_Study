package com.zss.shirodemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ZSS
 * @date 2019/9/25 15:25
 * @description 用户实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer userId;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

}
