package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {
    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端的测试方法111111111111");
    }
    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端的测试方法222222222222");
    }
    @Test(groups = "client")
    public void test3(){
        System.out.println("这是服务端的测试方法333333333333");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("这是服务端的测试方法444444444444");
    }
    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.println("这是服务端组运行之前运行的方法");
    }
    @AfterGroups("server")
    public void afterGroupsOnServer(){
        System.out.println("这是服务端组运行之后运行的方法");
    }
}
