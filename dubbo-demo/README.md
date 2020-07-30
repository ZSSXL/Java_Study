### DUBBO项目开发DEMO

### 开发技术：

SpringBoot + Dubbo + zookeeper



### 开发环境 win10

### 开发工具 IDEA



#### 遇到的问题

一开始运行服务提供者的时候一直失败，提示连接zookeeper超时失败

```TXT
错误信息： Will not attempt to authenticate using SASL (unknown error) 
```

#### 解决方法

在本机的hosts端添加IP地址映射，只需添加：ip地址	服务器名称

