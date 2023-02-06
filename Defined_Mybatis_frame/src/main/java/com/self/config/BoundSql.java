package com.self.config;

import com.self.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {
    //解析后的sql语句
    private String sqlText;

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappings) {
        this.sqlText = sqlText;
        this.parameterMappings = parameterMappings;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
