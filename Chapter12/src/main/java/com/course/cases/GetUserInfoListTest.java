package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户信息")
    public void GetUserList() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        GetUserListCase getUserInfoListCase = sqlSession.selectOne("getUserListCase",1);
        System.out.println(getUserInfoListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
        //发送请求，获取结果
        JSONArray resultJson = getJsonResult(getUserInfoListCase);
        //验证返回结果，getUserInfoListCase.getExpected()预期结果是一条sql语句
        List<User> userList = sqlSession.selectList(getUserInfoListCase.getExpected(),getUserInfoListCase);
        for (User u : userList){
            System.out.println("获取的user:"+u.toString());
        }
        JSONArray jsonArray = new JSONArray(userList);
        Assert.assertEquals(jsonArray.length(),resultJson);
        for (int i=0;i<resultJson.length();i++){
            JSONObject except = (JSONObject) jsonArray.get(i);
            JSONObject acyual = (JSONObject) resultJson.get(i);
            Assert.assertEquals(except,acyual);
        }
    }

    private JSONArray getJsonResult(GetUserListCase getUserInfoListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",getUserInfoListCase.getUserName());
        jsonObject.put("sex",getUserInfoListCase.getSex());
        jsonObject.put("age",getUserInfoListCase.getAge());
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);
        return  jsonArray;
    }
}
