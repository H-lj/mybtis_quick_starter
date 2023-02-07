package com.self.test;

import com.self.project.dao.IUserDao;
import com.self.project.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    @Test
    public void testFindAll1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        List<User> users = sqlSession.selectList("user.findAll");
        for (User user:users) {
            System.out.println(user.toString());
        }
        sqlSession.close();
    }

    @Test
    public void testFindAll2() throws IOException {
        //IUserDao iUserDao = new IUserDaoImpl();
       // List<User> users = iUserDao.findAll(new User());
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        List<User> users = sqlSession.getMapper(IUserDao.class).findAll(new User());
        for (User user:users) {
            System.out.println(user.toString());
        }
    }
    @Test
    public void testAdd() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        User user = new User();
        user.setId(1);
        user.setUserName("李四");
        sqlSession.insert("com.self.project.dao.IUserDao.add",user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession(true);
        User user = new User();
        user.setId(2);
        user.setUserName("张三四");
        sqlSession.update("com.self.project.dao.IUserDao.updateOne",user);
        sqlSession.close();
    }

    @Test
    public void testRemove() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        sqlSession.update("com.self.project.dao.IUserDao.remove",1);
        sqlSession.commit();
        sqlSession.close();
    }
}
