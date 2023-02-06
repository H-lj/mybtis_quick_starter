package com.self.sqlSession;

import com.self.config.BoundSql;
import com.self.frame.Configuration;
import com.self.frame.MappedStatement;
import com.self.utils.GenericTokenParser;
import com.self.utils.ParameterMapping;
import com.self.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        //注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        //获取sql语句,转换sql语句，#{}--> ？,解析#{}里面的值进行存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        //获取预处理对象，parameterStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //设置参数
        String paramterType = mappedStatement.getParamterType();
        Class<?> parameterClass = getClassType(paramterType);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            String content = parameterMapping.getContent();

            //反射
            Field declaredField = parameterClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }
        //执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //封装返回结果集
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //获取字段名
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                //使用反射，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);

            }
            objects.add(o);
        }

        return (List<E>) objects;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;
    }

    /**
     * 完成sql语句的转换，#{}--> ？,解析#{}里面的值进行存储
     *
     * @param sql Mapper中的sql
     * @return BoundSql
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        //解析后的sql语句，#{}--> ？
        String parseSql = genericTokenParser.parse(sql);
        //解析#{}里面的参数名称
        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}
