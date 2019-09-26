package com.zss.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZSS
 * @description 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.zss.shirodemo.mapper"})
public class ShiroDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroDemoApplication.class, args);
    }

}
