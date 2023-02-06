package com.self.config;

import com.self.frame.Configuration;
import com.self.frame.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {
    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement statement = new MappedStatement();
            statement.setId(id);
            statement.setResultType(resultType);
            statement.setParamterType(paramterType);
            statement.setSql(sqlText);
            String key = namespace + "." + id;
            configuration.getStatementMap().put(key, statement);
        }
    }
}
