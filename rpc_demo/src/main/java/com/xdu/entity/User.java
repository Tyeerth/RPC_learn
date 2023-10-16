package com.xdu.entity;

/**
 * @author tyeerth
 * @date 2023/9/5 - 下午2:21
 * @description
 */
public class User {
    private String username;

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;
}
