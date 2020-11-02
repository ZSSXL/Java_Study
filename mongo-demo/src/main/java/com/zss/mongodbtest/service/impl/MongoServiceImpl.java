package com.zss.mongodbtest.service.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.zss.mongodbtest.service.MongoService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/2 10:28
 * @desc mongodb 服务层接口方法实现
 */
@Slf4j
@Service
public class MongoServiceImpl implements MongoService {

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Override
    public void view(GridFSFile fsFile, HttpServletResponse response) {
        String fileName = fsFile.getFilename();
        GridFsResource resource = gridFsTemplate.getResource(fsFile);
        try {
            OutputStream out = response.getOutputStream();
            // 获取byte[]
            byte[] bytes = IOUtils.toByteArray(resource.getInputStream());
            // 将byte[]放入输出流
            out.write(bytes);
            //取后缀
            String sub = fileName.substring(fileName.lastIndexOf(".") + 1);
            log.info("预览原图[{}]", fileName);
            response.setHeader("Content-disposition", "inline; filename=" + fileName);
            response.setContentType("image/" + sub);
        } catch (IOException e) {
            log.error("预览图片[{}]失败:[{}]", fileName, e.getMessage());
        }
    }

    @Override
    public void viewCompression(GridFSFile fsFile, HttpServletResponse response) {
        String fileName = fsFile.getFilename();
        GridFsResource resource = gridFsTemplate.getResource(fsFile);
        try {
            OutputStream out = response.getOutputStream();
            /*
             * 图片操作
             * .size(int x, int y) - 指定大小
             * .keepAspectRatio(true) - 是否按比例进行变换（默认true）
             * question: 压缩大图片有点慢，2Mb以上的图片压缩需要3秒以上
             */
            Thumbnails.of(resource.getInputStream())
                    // 按比例缩放 - 20%
                    .scale(0.2f)
                    // 透明度 - 80%
                    .outputQuality(0.8f)
                    // 输出
                    .toOutputStream(out);
            //取后缀
            String sub = fileName.substring(fileName.lastIndexOf(".") + 1);
            log.info("预览压缩图片[{}]", fileName);
            response.setHeader("Content-disposition", "inline; filename=" + fileName);
            response.setContentType("image/" + sub);
        } catch (IOException e) {
            log.error("预览图片[{}]失败:[{}]", fileName, e.getMessage());
        }
    }
}
