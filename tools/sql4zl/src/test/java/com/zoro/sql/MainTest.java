package com.zoro.sql;

import com.zoro.sql.config.DataBaseConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: Java-Package
 * @description: a test class
 * @author: Zoro Li
 * @create: 2019-10-31 09:48
 */
public class MainTest {

    private SessonFactory sessonFactory = new SessonFactory();

    @Before
    public void init(){
    }

    @After
    public void destory(){

    }

    @Test
    public void  mainTest(){
        sessonFactory.loadConfig(DataBaseConfig.class);
    }
}