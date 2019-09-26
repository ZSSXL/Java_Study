package com.zss.shirodemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2019/9/25 15:26
 * @description 权限实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private Integer permissionId;

    private String permissionName;

    private String url;

}
