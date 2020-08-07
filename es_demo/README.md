### ElasticSearch Study

##### Author: ZSS
##### UpdateTime: 2020-08-06
##### Desc: 这是一个ElasticSearch的demo



1. 在@Document中，type已经失效

   > 原因:  从ES6.0开始，官方便不建议一个索引中创建多个类型；在ES7.0中，更是移除了类型(Type)这个概念
   >
   > 解决:  一个实体一个索引