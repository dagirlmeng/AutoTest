package com.course.testng;

import org.testng.annotations.Test;

/**
 * 依赖测试，test2依赖于test1
 * test1先执行，然后再执行test2
 * 如果test1执行失败了，test2就不执行了
 */
public class DependTest {
    @Test
    public void test1(){
        System.out.println("test1 run");
        throw new RuntimeException();
    }
    @Test(dependsOnMethods = "test1")
    public void test2(){
        System.out.println("test2 run");
    }
}
