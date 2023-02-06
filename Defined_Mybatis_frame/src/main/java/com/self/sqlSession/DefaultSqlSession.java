package com.self.sqlSession;

import com.self.frame.Configuration;
import com.self.frame.MappedStatement;

import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        //将要完成SimpleExecutor里的query方法的调用
        SimpleExecutor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getStatementMap().get(statementId);
        List<E> query = executor.query(configuration, mappedStatement, params);
        return query;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或返回结果过多");
        }
    }
}
