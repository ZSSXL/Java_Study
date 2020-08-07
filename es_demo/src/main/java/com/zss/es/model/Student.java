package com.zss.es.model;

import com.zss.es.common.AnalyzerType;
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
 * @date 2020/8/6 15:31
 * @desc 学生 - 实体
 * \@Document 标记为文档类型
 * indexName：对应索引库名称
 * type：对应在索引库中的类型，注：
 * shards：分片数量，默认1
 * replicas：副本数量，默认1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "es_student")
public class Student implements Serializable {

    /**
     * 学号
     */
    @Id
    private String stuId;

    /**
     * 名称
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
     * 班级
     */
    @Field(type = FieldType.Keyword, analyzer = AnalyzerType.IK_MAX_WORD)
    private String desc;
}
