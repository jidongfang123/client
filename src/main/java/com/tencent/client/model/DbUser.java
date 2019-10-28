package com.tencent.client.model;

public class DbUser {
    private String name;

    private String password;

    private Integer age;

    private String address;

    private Integer sex;

    public DbUser(String name, String password, Integer age, String address, Integer sex) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.address = address;
        this.sex = sex;
    }

    public DbUser() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}