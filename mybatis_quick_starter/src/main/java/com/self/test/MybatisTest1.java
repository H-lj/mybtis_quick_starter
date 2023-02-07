package com.self.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.self.mapper.IUserMapper;
import com.self.project.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest1 {

    private IUserMapper userMapper;
    private SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void testFindAll() throws IOException {
        List<User> users = userMapper.findAll(new User());
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testAdd() throws IOException {
        User user = new User();
        user.setId(1);
        user.setUserName("测试数据");
        userMapper.add(user);
    }

    @Test
    public void testUpdate() throws IOException {
        User user = new User();
        user.setId(1);
        user.setUserName("测试数据修改了");
        userMapper.update(user);
    }

    @Test
    public void testRemove() throws IOException {
        User user = new User();
        user.setId(1);
        userMapper.remove(user);
    }

    @Test
    public void testPageHelper() throws IOException {
        PageHelper.startPage(3,10);
        List<User> users = userMapper.findAll(new User());
        for (User user:users) {
            System.out.println(user.toString());
        }
        PageInfo<User> userPageInfo = new PageInfo<>(users);

        System.out.println("===========");
        System.out.println(userPageInfo);
        System.out.println("===========");

    }

//    @After
//    public void after(){
//        sqlSession.close();
//    }

}
