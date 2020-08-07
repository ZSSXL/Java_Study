package com.zss.es.controller;

import com.zss.es.model.Student;
import com.zss.es.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 15:33
 * @desc Student - 控制器
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 查询
     *
     * @param desc 描述
     * @return List<Student>
     */
    @GetMapping("/{desc}")
    public List<Student> selectAllStudent(@PathVariable("desc") String desc) {
        if ("".equals(desc)) {
            return null;
        } else {
            return studentService.selectAllStudentByDesc(desc);
        }
    }
}
