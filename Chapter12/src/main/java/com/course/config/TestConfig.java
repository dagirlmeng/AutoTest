package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestConfig {
    //登录接口uri
    public static String loginUrl;
    //更新用户接口uri
    public static String updateUserInfoUrl;
    //获取用户列表接口uri
    public static String getUserListUrl;
    //获取用户接口uri
    public static String getUserInfoUrl;
    //添加用户接口uri
    public static String addUserUrl;
    //生命http客户端
    public static DefaultHttpClient defaultHttpClient;
    public static CookieStore cookieStore;
}
