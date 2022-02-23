package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
@RequestMapping("v1")
public class MyPostMethod {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String lohin(HttpServletResponse response,
                        //value = "userName"--前端对应的名字，required = true--标注这个参数不能为空，必须传值
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "passWord",required = true) String password){
        if (userName.equals("zhangsan")&&password.equals("123")){
            Cookie cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名或密码错误";
    }
}
