package com.zoro.sql.annotation;


import com.zoro.sql.enums.AllowNull;
import com.zoro.sql.enums.ColumnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String name() default "";
    int length() default 10;
    ColumnType type() default ColumnType.VARCHAR;
    String info() default "";
    AllowNull allowNull() default AllowNull.NULL;

}
