package com.zss.mongodbtest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/2 9:37
 * @desc gridFsTemplate 测试
 */
@Slf4j
public class GridFsTemplateTest extends BaseTest {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Test
    public void saveImage() {
        String filePath = "D:/2236037.jpg";
        String fileName = "2236037";
        MultipartFile file = getMulFileByPath(filePath, fileName);
        System.out.println("FileName : " + file.getOriginalFilename());
        ObjectId objectId = null;
        try {
            objectId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        } catch (IOException e) {
            log.error("保存图片失败：[{}]", e.getMessage());
        }
        if (objectId != null) {
            System.out.println(objectId.toString());
            // 5f9fb620283ca11eed240d40
        } else {
            System.out.println("Nothing in here!!");
        }
    }

    // ======================== 内部方法 将file转化为multipartFile ======================== //

    /**
     * 将File转换为MultipartFile
     *
     * @param filePath 文件路径
     * @return 转换后的MultipartFile对象
     */
    private static MultipartFile getMulFileByPath(String filePath, String fileName) {
        FileItem fileItem = createFileItem(filePath, fileName);
        return new CommonsMultipartFile(fileItem);
    }

    /**
     * 通过文件路径将指定文件转化为FileItem对象
     *
     * @param filePath 文件路径
     * @return 转换后的FileItem对象
     */
    private static FileItem createFileItem(String filePath, String fileName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "text/plain", true,
                fileName + extFile);
        File newFile = new File(filePath);
        int bytesRead;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(newFile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192))
                    != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}
