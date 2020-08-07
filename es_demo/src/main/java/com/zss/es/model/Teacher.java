package com.zss.es.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 9:24
 * @desc 教师 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "es_teacher")
public class Teacher implements Serializable {

    /**
     * 教师Id
     */
    @Id
    private String teacherId;

    /**
     * 教师名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 true：男   false：女
     */
    private Boolean gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 简介
     */
    @Field(type = FieldType.Keyword, analyzer = "ik_max_word")
    private String desc;

}
