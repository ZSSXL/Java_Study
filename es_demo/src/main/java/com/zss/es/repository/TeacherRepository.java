package com.zss.es.repository;

import com.zss.es.model.Teacher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 9:37
 * @desc Teacher - 持久化
 */
@Repository
public interface TeacherRepository extends ElasticsearchRepository<Teacher, String> {

    /**
     * 通过描述获取老师信息
     *
     * @param desc 秒速信息
     * @return List<Teacher>
     */
    List<Teacher> findAllByDesc(String desc);
}
