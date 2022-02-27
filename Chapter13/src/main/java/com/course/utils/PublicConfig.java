package com.course.utils;

import lombok.extern.log4j.Log4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
@Log4j
public class PublicConfig {
    public static Boolean verifyCookies(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("cookies为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("true")&&cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
