package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name="+name+"age="+age);
    }
    @DataProvider(name="data")
    public Object[] [] providerData(){
        Object[] [] o =new Object[][]{
                {"张三",20},
                {"李四",30},
                {"王五",10}
        };
        return o;
    }
}
