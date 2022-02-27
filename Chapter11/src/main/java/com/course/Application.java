package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * @SpringBootApplication放置在Springboot启动类上，表明该类是开启Springboot容器的入口，它是一个复合注解。
 * 里面包含了包扫描，自动注入，配置注入的功能，下面就给大家介绍里面的注解
 * 启动类里面使用@EnableScheduling 注解开启功能，自动扫描
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context= SpringApplication.run(Application.class,args);
    }
    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
