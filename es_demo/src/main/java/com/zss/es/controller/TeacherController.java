package com.zss.es.controller;

import com.zss.es.model.Teacher;
import com.zss.es.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 15:33
 * @desc Teacher - 控制器
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 查询
     *
     * @param desc 描述
     * @return List<Teacher>
     */
    @GetMapping("/{desc}")
    public List<Teacher> selectAllTeacher(@PathVariable("desc") String desc) {
        if ("".equals(desc)) {
            return null;
        } else {
            return teacherService.selectAllTeacherByDesc(desc);
        }
    }
}
