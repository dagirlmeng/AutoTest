package com.course.testng.suite;

import org.testng.annotations.Test;

/**
 * 逻辑控制类，test标签包含的那些方法
 */
public class LoginTest {
    @Test
    public void loginTaoBao(){
        System.out.println("淘宝登陆成功");
    }
}
