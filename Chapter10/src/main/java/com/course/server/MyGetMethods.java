package com.course.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * Spring中@RestController的作用等同于@Controller + @ResponseBody
 * @Controller注解，表明了这个类是一个控制器类
 * @RequestMapping注解是用来映射请求的，即指明处理器可以处理哪些URL请求，该注解既可以用在类上，也可以用在方法上
 * @ResponseBody表示方法的返回值直接以指定的格式写入Http response body中，而不是解析为跳转路径,
 * 返回实体对象或者字符串时，就会自动转换成json对象传给前端
 */
@RestController
public class MyGetMethods {
    //method:必须使用xx方法来访问
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest装请求信息的类
        //HttpServletResponse装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }
}
