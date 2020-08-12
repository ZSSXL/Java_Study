package com.zss.lock;

import com.zss.lock.properties.LettuceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/12 14:23
 * @desc 启动类
 */
@SpringBootApplication
@EnableConfigurationProperties(LettuceProperties.class)
public class LockApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockApplication.class, args);
    }
}
