package com.zss.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author ZSS
 * @date 2020-08-07
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.zss.es.repository")
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

}
