package com.zss.app.service.impl;

import com.zss.app.service.AppService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/1 15:32
 * @desc 服务层接口实现
 */
@Service
public class AppServiceImpl implements AppService {

    @Override
    public String print(String content) {
        System.out.println("Content: [ " + content + " ]");
        return content + ", hi!";
    }
}
