package com.zss.postgres_demo.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/7 9:20
 * @desc 加载 dozer 配置文件
 */
@Configuration
public class DozerBeanMapperConfigure {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Collections.singletonList("dozer/dozer-conf.xml"));
        return mapper;
    }
}
