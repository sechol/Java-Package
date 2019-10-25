package com.zoro.love;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program: Java-Package
 * @description: Application Start Class
 * @author: Zoro Li
 * @create: 2019-10-24 17:34
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LoveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveApplication.class,args);
    }

}