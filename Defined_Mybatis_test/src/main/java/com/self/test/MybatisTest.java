package com.self.test;

import com.self.io.Resources;
import com.self.poro.User;
import com.self.sqlSession.SqlSession;
import com.self.sqlSession.SqlSessionFactory;
import com.self.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class MybatisTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = build.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUserName("小明");
        User userResult = sqlSession.selectOne("user.selectOne", user);
        System.out.println(userResult);
    }
}