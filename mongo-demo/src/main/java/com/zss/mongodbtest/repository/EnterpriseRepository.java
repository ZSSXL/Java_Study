package com.zss.mongodbtest.repository;

import com.zss.mongodbtest.model.entity.Enterprise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:25
 * @desc enterprise 持久层
 */
@Repository
public interface EnterpriseRepository extends MongoRepository<Enterprise, String> {
}
