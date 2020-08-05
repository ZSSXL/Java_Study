package com.zss.lambda.model;

import java.util.Objects;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 13:38
 * @desc User - 实体
 */
@SuppressWarnings("unused")
public class User {

    private String username;

    private String password;

    private Integer age;


    public User() {
        System.out.println("无参执行。。。。");
    }

    public User(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
        System.out.println("有参执行。。。。");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, age);
    }
}
