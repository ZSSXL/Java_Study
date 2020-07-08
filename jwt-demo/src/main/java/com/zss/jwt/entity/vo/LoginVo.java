package com.zss.jwt.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/8 15:53
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo implements Serializable {

    private String username;

    private String password;
}
