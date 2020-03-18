package com.zss.sftpdemo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZSS
 * @date 2020/3/18 21:20
 * @description
 */
public interface FtpService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return String
     */
    String uploadFile(MultipartFile file, String path);
}
