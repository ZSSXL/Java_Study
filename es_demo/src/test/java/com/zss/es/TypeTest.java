package com.zss.es;

import com.zss.es.model.Student;
import com.zss.es.model.Teacher;
import com.zss.es.repository.StudentRepository;
import com.zss.es.repository.TeacherRepository;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 13:51
 * @desc Type - 测试
 */
public class TypeTest extends BaseTest {

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private TeacherRepository teacherRepository;

    @Test
    public void addNewStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("10001", "小明", 23, true, "我是小学二年级三班的小明同学"));
        students.add(new Student("10002", "小强", 22, true, "我是初中三年级三班的小强同学"));
        students.add(new Student("10003", "小芳", 18, false, "我是高中三年级三班的小芳同学"));
        students.add(new Student("10004", "小白", 21, true, "我是大学二年级三班的小白同学"));
        students.add(new Student("10005", "小王", 22, true, "我是研究生二年级三班的小王同学"));
        studentRepository.saveAll(students);
    }

    @Test
    public void findAllTest() {
        Iterable<Student> all = studentRepository.findAll();
        all.forEach(stu -> System.out.println("Student : " + stu));
    }

    @Test
    public void addNewTeacher() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("20001", "阿大", 23, true, "13495847493", "我是小学三年级的班主任"));
        teachers.add(new Teacher("20002", "阿二", 23, true, "13495847493", "我是初中三年级的班主任"));
        teachers.add(new Teacher("20003", "阿三", 23, true, "13495847493", "我是高中三年级的班主任"));
        teachers.add(new Teacher("20004", "阿四", 23, true, "13495847493", "我是大学三年级的班主任"));
        teachers.add(new Teacher("20005", "阿五", 23, true, "13495847493", "我是研究生三年级的班主任"));
        teachers.add(new Teacher("20006", "阿六", 23, true, "13495847493", "我是博士生导师"));
        teacherRepository.saveAll(teachers);
        System.out.println("Finished .....");
    }

    @Test
    public void findAllTeacher() {
        Iterable<Teacher> all = teacherRepository.findAll();
        all.forEach(tea -> System.out.println("Teacher : " + tea));
    }
}
