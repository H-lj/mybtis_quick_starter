package com.self.sqlSession;

import com.self.frame.Configuration;
import com.self.frame.MappedStatement;

import java.util.List;

public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
