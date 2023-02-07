package com.self.test;

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

public class CacheTest {
    private IUserMapper userMapper;
    private SqlSession sqlSession;

    private SqlSessionFactory build;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession();
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void firstLevelCache() throws IOException {
        //第一次查询
        User user1 = userMapper.selectById(2);

        //更新对象
        User user = new User();
        user.setId(2);
        user.setUserName("修改了");
        userMapper.update(user);
        //事务提交时会刷新一级缓存
        sqlSession.commit();
        //手动刷新一级缓存
        //sqlSession.clearCache();

        //第二次查询
        User user2 = userMapper.selectById(2);
        System.out.println(user1 == user2);
    }

    @Test
    public void twoLevelCache() throws IOException {
        SqlSession sqlSession1 = build.openSession();
        SqlSession sqlSession2 = build.openSession();
        SqlSession sqlSession3 = build.openSession();

        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);
        IUserMapper userMapper3 = sqlSession3.getMapper(IUserMapper.class);

        User user1 = userMapper1.selectById(2);
        sqlSession1.close();
        User user = new User();
        user.setId(2);
        user.setUserName("zhangSan");
        userMapper3.update(user);
        sqlSession3.commit();

        User user2 = userMapper2.selectById(2);

        System.out.println(user1 == user2);

    }

    @Test
    public void twoLevelCacheFromRedis() throws IOException {
        //先启动redis才能使用
        SqlSession sqlSession1 = build.openSession();
        SqlSession sqlSession2 = build.openSession();
        SqlSession sqlSession3 = build.openSession();

        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);
        IUserMapper userMapper3 = sqlSession3.getMapper(IUserMapper.class);

        User user1 = userMapper1.selectById(2);
        sqlSession1.close();


        User user2 = userMapper2.selectById(2);

        System.out.println(user1 == user2);

    }
}
