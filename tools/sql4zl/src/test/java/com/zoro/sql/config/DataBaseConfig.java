package com.zoro.sql.config;

import com.zoro.sql.annotation.databese.ModelScan;
import com.zoro.sql.annotation.databese.*;

/**
 * @program: Java-Package
 * @description: database config class
 * @author: Zoro Li
 * @create: 2019-10-31 10:23
 */

@DataBase
@ModelScan(path = "com.zoro.sql.model")
public class DataBaseConfig {

    @DB_Username
    private static final String USERNAME = "root";
    @DB_Password
    private static final String PASSWORD = "123456";
    @DB_Driver
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    @DB_URL
    private static final String URL = "jdbc:mysql://localhost:3306/db_love";

}