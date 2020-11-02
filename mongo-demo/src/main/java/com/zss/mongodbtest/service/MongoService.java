package com.zss.mongodbtest.service;

import com.mongodb.client.gridfs.model.GridFSFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/2 10:27
 * @desc mongodb
 */
public interface MongoService {

    /**
     * 下载文件
     *
     * @param fsFile   文件
     * @param response 响应
     */
    void view(GridFSFile fsFile, HttpServletResponse response);
}
