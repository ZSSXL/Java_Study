package com.zss.mongodbtest.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.zss.mongodbtest.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/2 9:30
 * @desc mongodb - controller
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {

    private final GridFsTemplate gridFsTemplate;
    private final MongoService mongoService;

    @Autowired
    public MongoController(GridFsTemplate gridFsTemplate, MongoService mongoService) {
        this.gridFsTemplate = gridFsTemplate;
        this.mongoService = mongoService;
    }

    /**
     * 查看原图
     *
     * @param response 相应
     * @return nothing
     */
    @GetMapping
    public String findOne(HttpServletResponse response) {
        String objectId = "5f9fbbc5dd2b6a657ae4f894";
        // 5f9fbbc5dd2b6a657ae4f894
        GridFSFile one = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(objectId)));
        if (one != null) {
            mongoService.view(one, response);
            return "yes";
        } else {
            return "Oh, no";
        }
    }

    /**
     * 查看缩略图
     *
     * @param response 相应
     */
    @GetMapping("/compression")
    public void viewCompression(HttpServletResponse response) {
        String objectId = "5f9fbbc5dd2b6a657ae4f894";
        GridFSFile one = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(objectId)));
        if (one != null) {
            mongoService.viewCompression(one, response);
        }
    }
}
