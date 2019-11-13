package com.zoro.sql;

import com.zoro.sql.annotation.*;
import com.zoro.sql.annotation.databese.*;
import com.zoro.sql.enums.AllowNull;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;


/**
 * @program: Java-Package
 * @description: A factory generate session
 * @author: Zoro Li
 * @create: 2019-10-25 15:12
 */
public class SessonFactory {

    private static String url = "";
    private static String driver = "";
    private static String username = "";
    private static String password = "";
    private static Class config = null;
    private ReflectUtil reflectUtil = new ReflectUtil();

    public void loadConfig(Class db_config) {
        reflectUtil.loadConnectorConfig(db_config);
        loadTables();
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnector() {
        Connection connection = null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadTables() {
        Connection connection = getConnector();
        if (connection != null) {
            String path = reflectUtil.getTableMappingPath(config).replace(".", "/");
            String loadPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("%20", " ") + path;
            FileUtils.listFiles(new File(loadPath), FileFilterUtils.suffixFileFilter("class"), null).forEach((file) -> {
                try {
                    Class tableMapping = Class.forName(reflectUtil.getTableMappingPath(config) + "." + file.getName().replace(".class", ""));
                    reflectUtil.tableMapping(tableMapping);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static class ReflectUtil {
        private SqlUtil sqlUtil = new SqlUtil();

        void loadConnectorConfig(Class db_config) {
            config = db_config;
            if (db_config.getAnnotation(DataBase.class) != null) {
                Field[] fields = db_config.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getAnnotation(DB_URL.class) != null) {
                        try {
                            url = (String) field.get(null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (field.getAnnotation(DB_Driver.class) != null) {
                        try {
                            driver = (String) field.get(null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (field.getAnnotation(DB_Username.class) != null) {
                        try {
                            username = (String) field.get(null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (field.getAnnotation(DB_Password.class) != null) {
                        try {
                            password = (String) field.get(null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        String getTableMappingPath(Class db_config) {
            try {
                ModelScan path = (ModelScan) db_config.getAnnotation(ModelScan.class);
                return path.path();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        void tableMapping(Class table) {
            StringBuilder sql = new StringBuilder("");
            Connection connection = getConnector();
            if (connection != null) {
                if ((table.getAnnotation(Table.class) != null) &&
                        (table.getAnnotation(Model.class) != null)) {
                    Table tableEntity = (Table) table.getAnnotation(Table.class);
                    String tableName = tableEntity.value();
                    if (tableName.equals("")) {
                        tableName = "t_" + table.getSimpleName().toLowerCase();
                    }
                    PreparedStatement statement = null;
                    ResultSet resultSet = null;
                    try {
                        statement = connection.prepareStatement("select count(*) from " + tableName);
                        resultSet = statement.executeQuery();
                    } catch (SQLException e) {
                        sql.append("create table ").append(tableName).append("(");
                        Field[] fields = table.getDeclaredFields();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            Annotation[] annotations = field.getDeclaredAnnotations();
                            for (Annotation annotation : annotations) {
                                sql.append(getColumnConfig(annotation, field.getName()));
                            }
                        }
                        sql.deleteCharAt(sql.length() - 1);
                        sql.append(");");
                        try {
                            statement = connection.prepareStatement(sql.toString());
                            statement.executeUpdate();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

        String getColumnConfig(Annotation annotation, String columnName) {
            if (annotation instanceof Id) {
                return columnName + " INT(10) UNSIGNED NOT NULL,";
            }
            /*if (annotation instanceof GeneratedValue) {
                return " AUTO_INCREMENT,";
            }*/
            if (annotation instanceof Column) {
                Column column = (Column) annotation;
                return getColumnDetailInfo(column, columnName);
            }
            return "";
        }

        String getColumnDetailInfo(Column column, String columnName) {
            StringBuilder builder = new StringBuilder();
            builder.append(columnName+" ");
            int length = column.length();
            switch (column.type()) {
                case CHAR: {
                    builder.append("CHAR(").append(length).append(")");
                    break;
                }
                case VARCHAR: {
                    builder.append("VARCHAR(").append(length).append(")");
                    break;
                }
                case SMALLINT: {
                    builder.append("SMAllINT(").append(length).append(")");
                    break;
                }
                case INT: {
                    builder.append("INT(").append(length).append(")");
                    break;
                }
                case BIGINT: {
                    builder.append("BIGINT(").append(length).append(")");
                    break;
                }
                case LONG: {
                    builder.append("LONG(").append(length).append(")");
                    break;
                }
                case DOUBLE: {
                    builder.append("DOUBLE(").append(length).append(")");
                    break;
                }
                case BLOG: {
                    builder.append("BLOG");
                    break;
                }
                case TEXT: {
                    builder.append("TEXT");
                    break;
                }
                case DATE: {
                    builder.append("DATE");
                    break;
                }
                case TIMESTAMP: {
                    builder.append("TIMESTAMP");
                    break;
                }
            }
            if (AllowNull.NOT_NULL == column.allowNull()) {
                builder.append(" NOT NULL");
            }
            return builder.append(",").toString();
        }
    }

    private static class SqlUtil {


    }

}



