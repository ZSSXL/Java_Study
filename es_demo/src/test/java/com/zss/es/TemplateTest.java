package com.zss.es;

import com.zss.es.model.Student;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import javax.annotation.Resource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 9:43
 * @desc ES index索引 - 测试
 */
public class TemplateTest extends BaseTest {

    @Resource
    private ElasticsearchRestTemplate esRestTemplate;

    public void createIndex() {
        // esRestTemplate.createIndex(Student.class) - 该方法以过时
        // 设置索引信息（绑定实体类）， 返回IndexOperations
        IndexOperations stuIndexOpts = esRestTemplate.indexOps(Student.class);
        // 创建索引库
        boolean create = stuIndexOpts.create();
        System.out.println("Index Create : " + create);
        // 为该IndexOperations绑定到的实体创建索引映射。  有一个为给定类创建索引的重载,需要类的字节码文件
        Document mapping = stuIndexOpts.createMapping();
        // 将刚刚通过类创建的映射写入索引
        stuIndexOpts.putMapping(mapping);
        System.out.println("Mapping : " + mapping.toString());
    }

}
