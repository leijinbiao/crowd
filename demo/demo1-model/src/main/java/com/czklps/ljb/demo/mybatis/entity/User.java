package com.czklps.ljb.demo.mybatis.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-mm-dd-ss")
    private Date createtime;

    private String email;

    public User() {
    }

    public User(Integer id, String username, String password, String phone, Date createtime, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.createtime = createtime;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", createtime=" + createtime +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}