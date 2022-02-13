package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml1 {
    @Test
    public void test1(){
        System.out.printf("Thread Id1111 : %s%n",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.printf("Thread Id1111 : %s%n",Thread.currentThread().getId());
    }
}
