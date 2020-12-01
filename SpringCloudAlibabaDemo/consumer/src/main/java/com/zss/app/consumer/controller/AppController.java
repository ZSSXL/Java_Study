package com.zss.app.consumer.controller;

import com.zss.app.service.AppService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/1 15:40
 * @desc 消费调用接口端
 */
@RestController
@RequestMapping("/app")
@SuppressWarnings("unused")
public class AppController {

    @Reference
    private AppService appService;

    @GetMapping("/{content}")
    public String print(@PathVariable("content") String content) {
        return appService.print(content);
    }
}
