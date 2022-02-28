package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        AddUserCase addUserCase = sqlSession.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);
        //发请求，获取结果
        String result = getResult(addUserCase);
        //验证返回结果
        User user = sqlSession.selectOne("addUser",addUserCase);
        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject json = new JSONObject();
        json.put("userName",addUserCase.getUserName());
        json.put("password",addUserCase.getPassword());
        json.put("sex",addUserCase.getSex());
        json.put("age",addUserCase.getAge());
        json.put("permission",addUserCase.getPermission());
        json.put("isDelete",addUserCase.getIsDelete());
        //设置头信息
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore( TestConfig.cookieStore);
        //存放返回结果
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }
}
