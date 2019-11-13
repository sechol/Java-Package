package com.zoro.sql.model;

import com.zoro.sql.annotation.*;
import com.zoro.sql.enums.AllowNull;
import com.zoro.sql.enums.ColumnType;

/**
 * @program: Java-Package
 * @description: A entity Class
 * @author: Zoro Li
 * @create: 2019-10-31 09:57
 */
@Model
@Table(value = "t_student")
public class Student {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "username",type = ColumnType.VARCHAR,length = 15,allowNull = AllowNull.NOT_NULL)
    private String username;
    @Column(name = "password",type = ColumnType.VARCHAR,length = 20,allowNull = AllowNull.NOT_NULL)
    private String password;

    public Student() {
    }

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}