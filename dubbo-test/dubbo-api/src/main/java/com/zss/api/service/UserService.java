package com.zss.api.service;

import com.zss.api.entity.User;

/**
 * @author ZSS
 * @company 上海数慧系统技术有限公司
 * @Dept 数据中心
 * @email zhoushs@dist.com.cn
 * @date 2020/6/28 15:27
 * @Desc user 服务层接口
 */
public interface UserService {

    /**
     * 连接测试
     *
     * @param content 输入内容
     * @return String
     */
    String sayHello(String content);

    /**
     * 接口测试
     *
     * @param user 用户实体
     * @return User
     */
    User helloUser(User user);
}
