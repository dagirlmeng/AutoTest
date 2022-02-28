package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTrue",description = "测试正准备工作，获取httpClient对象")
    public void beforeTest(){
        TestConfig.getUserInfoUrl= ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl= ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl= ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl= ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description = "用户登陆成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求
        String result = getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    @Test(groups = "loginFalse",description = "用户登陆失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase",2);
        //发送请求
        String result = getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject json = new JSONObject();
        json.put("userName",loginCase.getUserName());
        json.put("password",loginCase.getPassword());
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        post.setEntity(entity);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        TestConfig.cookieStore = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }

}
