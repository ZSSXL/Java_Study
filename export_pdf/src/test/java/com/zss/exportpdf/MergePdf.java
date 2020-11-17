package com.zss.exportpdf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/11 17:42
 * @desc 合并pdf文件
 */
public class MergePdf extends BaseTest {

    public static void main(String[] args) {
        String dirPath = "C:/Users/12711/Desktop/数慧周报";
        MergePdf mergePdf = new MergePdf();
        List<String> allFile = mergePdf.getAllFile(dirPath, false);
        allFile.forEach(item -> System.out.println("DIR: " + item));
    }

    /**
     * 获取路径下所有的文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否包括子文件夹的路径
     * @return 结果
     */
    public List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (isAddDirectory) {
                        list.add(file.getAbsolutePath());
                    }
                    list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
                } else {
                    list.add(file.getAbsolutePath());
                }
            }
        }
        return list;
    }
}
