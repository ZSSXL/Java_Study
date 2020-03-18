package com.zss.sftpdemo.util;

import com.zss.sftpdemo.common.config.FtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ZSS
 * @date 2020/3/18 20:31
 * @description 文件工具包
 */
@Slf4j
@Component
public class FtpUtil {

    private static FTPClient ftpClient;

    /**
     * 初始化ftp服务器
     *
     * @throws IOException
     */
    public static void initFtpClient() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        log.info("connecting...ftp 服务器:" + FtpProperties.FTP_IP + ":" + FtpProperties.FTP_PORT);

        // 连接ftp服务器
        ftpClient.connect(FtpProperties.FTP_IP, FtpProperties.FTP_PORT);
        boolean login = ftpClient.login(FtpProperties.FTP_USERNAME, FtpProperties.FTP_PASSWORD);
        System.out.println("ftp login : " + login);
        // 登录ftp服务器
        if (!login) {
            log.info("connect failed... ftp服务器:" + FtpProperties.FTP_IP + ":" + FtpProperties.FTP_PORT);
        }
        log.info("connect successful... ftp服务器:" + FtpProperties.FTP_IP + ":" + FtpProperties.FTP_PORT);
    }

    /**
     * 上传文件
     *
     * @param pathName    路径名
     * @param fileName    文件名
     * @param inputStream 文件流
     * @return Boolean
     * @throws IOException
     */
    public static Boolean uploadFile(String pathName, String fileName, InputStream inputStream) throws IOException {
        initFtpClient();
        try {
            log.info("start upload file ...");
            // 文件类型设置成二进制类型，避免出现乱码
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 打开被动模式
            ftpClient.enterLocalPassiveMode();

            // 进入文件夹
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(pathName);
            if (changeWorkingDirectory) {
                log.info("进入文件夹[{}]成功...", pathName);
                ftpClient.changeWorkingDirectory(pathName);
            } else {
                log.info("进入文件夹[{}]失败...", pathName);
                boolean makeDirectory = ftpClient.makeDirectory(pathName);
                if (makeDirectory) {
                    log.info("创建文件夹[{}]成功...", pathName);
                    boolean changePath = ftpClient.changeWorkingDirectory(pathName);
                    if (changePath) {
                        log.info("进入文件夹[{}]成功...", pathName);
                    }
                } else {
                    log.info("创建文件夹[{}]失败...", pathName);
                }
            }
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            log.info("上传文件成功...");
            return true;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e1) {
                    log.error("ftp关闭连接失败", e1);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    log.error("关闭文件流失败...", e2);
                }
            }
        }
    }
}
