package com.course.controller;

import com.course.model.TestUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping(value = "v1")
public class Demo {
    //首先获取一个执行sql语句的对象,@Autowired启动及加载
    //demo
    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value = "getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }
    /**
     * 实现增删改查
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody TestUser testUser){
        int result = template.insert("addUser",testUser);
        return result;
    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public int updateUser(@RequestBody TestUser testUser){
        int result = template.insert("updateUser",testUser);
        return result;
    }
    @RequestMapping(value = "deleteUser",method = RequestMethod.GET)
    public int deleteUser(@RequestParam int id){
        return template.delete("deleteUser",id);
    }
}

