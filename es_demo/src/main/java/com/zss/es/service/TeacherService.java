package com.zss.es.service;

import com.zss.es.model.Teacher;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 15:31
 * @desc Teacher - 服务层接口
 */
public interface TeacherService {

    /**
     * 通过描述信息获取
     *
     * @param desc 描述信息
     * @return List<Teacher>
     */
    List<Teacher> selectAllTeacherByDesc(String desc);
}
