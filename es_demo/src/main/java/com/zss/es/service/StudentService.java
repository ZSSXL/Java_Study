package com.zss.es.service;

import com.zss.es.model.Student;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 15:31
 * @desc Student - 服务层接口
 */
public interface StudentService {

    /**
     * 通过描述获取学生信息
     *
     * @param desc 描述信息
     * @return 学生信息
     */
    List<Student> selectAllStudentByDesc(String desc);
}
