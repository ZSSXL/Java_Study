package com.zss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author ZSS
 * @company 上海数慧系统技术有限公司
 * @Dept 数据中心
 * @email zhoushs@dist.com.cn
 * @date 2020/6/28 16:57
 * @Desc
 */
@SpringBootApplication
@ImportResource("classpath:config/spring-dubbo.xml")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
