package com.zss.shirodemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ZSS
 * @date 2019/9/25 15:26
 * @description 角色实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private Integer roleId;

    private String roleName;

    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();

}
