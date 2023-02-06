package com.self.sqlSession;

import com.self.config.XMLConfigBuilder;
import com.self.frame.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //将解析出来的内容封装到Configuration容器中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

        //创建SqlSessionFactory对象: 工厂类：生产SqlSession:会话对象
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);


        return sqlSessionFactory;
    }
}
