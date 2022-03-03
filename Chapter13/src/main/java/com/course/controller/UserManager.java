package com.course.controller;

import com.course.model.User;
import com.course.utils.PublicConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j
@Api(value = "v1",description = "用户管理系统")
@RestController
@RequestMapping("v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i = sqlSessionTemplate.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询到的结果是"+i);
        if (i==1){
            log.info("登陆的用户是"+user.getUserName());
            return true;
        }
        return false;
    }
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = PublicConfig.verifyCookies(request);
        int result = 0;
        if (x!=false){
            result = sqlSessionTemplate.insert("addUser",user);
        }
        if (result>0){
            log.info("添加用户的数量是："+result);
            return true;
        }
        return false;
    }
    @ApiOperation(value = "更新删除用户接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = PublicConfig.verifyCookies(request);
        int result = 0;
        if (x==true){
            result=sqlSessionTemplate.update("updateUserInfo",user);
        }
        log.info("更新数据的条目数为："+result);
        return result;
    }
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = PublicConfig.verifyCookies(request);
        if (x==true){
            List<User> userList = sqlSessionTemplate.selectList("getUserInfo",user);
            log.info("getUserInfo获取的用户数量是"+userList.size());
            return userList;
        }else {
            return null;
       }
    }
}
