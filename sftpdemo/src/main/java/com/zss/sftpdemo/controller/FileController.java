package com.zss.sftpdemo.controller;

import com.zss.sftpdemo.common.Const;
import com.zss.sftpdemo.common.ServerResponse;
import com.zss.sftpdemo.common.config.FtpProperties;
import com.zss.sftpdemo.service.FtpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZSS
 * @date 2020/3/18 21:48
 * @description 文件操作 controller
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private final FtpService ftpService;

    @Autowired
    public FileController(FtpService ftpService) {
        this.ftpService = ftpService;
    }

    /**
     * 上传文件
     *
     * @param file    文件
     * @param request 请求
     * @return ServerResponse
     */
    @PostMapping
    public ServerResponse<String> upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String imgUrl = uploadCoverImg(file, request);
        if (StringUtils.isEmpty(imgUrl)) {
            log.info("上传文件失败");
            return ServerResponse.createByError();
        } else {
            log.info("上传文件成功，返回信息为：[{}]", imgUrl);
            return ServerResponse.createBySuccess();
        }
    }

    // ------------------------------ 私有工具 ------------------------------- //

    /**
     * 上传封面
     *
     * @param file    file
     * @param request request
     * @return String
     */
    private String uploadCoverImg(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        if (file != null) {
            String targetFileName = ftpService.uploadFile(file, path);
            log.info("上传封面成功:" + targetFileName);
            return FtpProperties.HTTP_PREFIX + Const.COVER_PATH + "/" + targetFileName;
        } else {
            return null;
        }
    }
}
