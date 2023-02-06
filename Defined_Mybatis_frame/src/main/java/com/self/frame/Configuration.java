package com.self.frame;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private DataSource dataSource;

    /*
     * key:由namespace.id组成--statementId
     * value:封装好的mappedStatement对象
     * */
    Map<String, MappedStatement> statementMap = new HashMap<>();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getStatementMap() {
        return statementMap;
    }

    public void setStatementMap(Map<String, MappedStatement> statementMap) {
        this.statementMap = statementMap;
    }
}
