package com.zss.es.service.impl;

import com.zss.es.model.Student;
import com.zss.es.repository.StudentRepository;
import com.zss.es.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 15:32
 * @desc Student - 实现服务层接口方法
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> selectAllStudentByDesc(String desc) {
        return studentRepository.findAllByDesc(desc);
    }
}
