package com.zss.lettuce;

import com.zss.lettuce.properties.LettuceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 10:58
 * @desc Lettuce - 启动类
 */
@SpringBootApplication
public class LettuceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LettuceProperties.class, args);
    }
}
