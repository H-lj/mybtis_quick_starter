package com.self.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )})
public class MyPlugin implements Interceptor {

    /**
     * 拦截方法：只要被拦截的目标对象的目标方法被执行时，每次都会执行intercept
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对方法进行了增强...");

        //分页，监控等等

        //原方法执行
        return invocation.proceed();
    }

    /**
     * 主要为了把当前拦截器生成代理存到拦截器链中
     *
     * @param o
     * @return
     */
    @Override
    public Object plugin(Object o) {
        Object wrap = Plugin.wrap(o, this);
        return wrap;
    }

    /**
     * 获取配置文件参数
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取到的配置文件的参数是：" + properties);
    }
}
