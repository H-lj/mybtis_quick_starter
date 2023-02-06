package com.self.sqlSession;

import java.util.List;

public interface SqlSession {
    /**
     * 查询所有
     */
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    /**
     * 根据条件查询一个
     */
    public <T> T selectOne(String statementId, Object... params) throws Exception;
}
