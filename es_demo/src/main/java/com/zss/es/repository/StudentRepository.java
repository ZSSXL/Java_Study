package com.zss.es.repository;

import com.zss.es.model.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 9:36
 * @desc Student - 持久层
 */
@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

    /**
     * 通过描述获取老师信息
     *
     * @param desc desc
     * @return 学生信息
     */
    List<Student> findAllByDesc(String desc);
}
