package com.zss.es.service.impl;

import com.zss.es.model.Teacher;
import com.zss.es.repository.TeacherRepository;
import com.zss.es.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 15:32
 * @desc Teacher - 实现服务层接口方法
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> selectAllTeacherByDesc(String desc) {
        return teacherRepository.findAllByDesc(desc);
    }
}
