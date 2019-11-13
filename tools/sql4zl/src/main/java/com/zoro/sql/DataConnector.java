package com.zoro.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Java-Package
 * @description: A class connect database
 * @author: Zoro Li
 * @create: 2019-10-25 16:38
 */
public class DataConnector {

    private Connection connection = null;
    private List<Class<?>> classLoaders = new ArrayList<Class<?>>();
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_love";

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close(DataConnector connector) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}