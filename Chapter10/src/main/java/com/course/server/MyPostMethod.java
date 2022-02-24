package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
@RequestMapping("v1")
public class MyPostMethod {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
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
    /**
     * 验证cookies，验证通过后返回用户列表
     */
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){
        User user1;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies是否合法
        for (Cookie c : cookies){
            if (c.getName().equals("login")
                    &&c.getValue().equals("true")
                    &&user.getUserName().equals("zhangsan")
                    &&user.getPassword().equals("123")){
                user1 = new User();
                user1.setName("lisi");
                user1.setAge("18");
                user1.setSex("man");
                return user1.toString();
            }
        }
        return "参数不合法";
    }
}
