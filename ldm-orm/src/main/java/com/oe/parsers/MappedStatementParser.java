package com.oe.parsers;

import com.oe.exception.ExceptionBuilder;
import com.oe.mappers.MappedStatement;
import com.oe.mappers.SqlCommandType;
import com.oe.utils.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.Locale;

public class MappedStatementParser implements Parser<MappedStatement> {

    private  MappedStatement mappedStatement;

    public MappedStatementParser() {
        this.mappedStatement = new MappedStatement();
    }

    public MappedStatement parse(Element sqlEle) {
        String name = sqlEle.getName();

        Attribute idAttribute = sqlEle.attribute("id");
        Attribute resultTypeAttribute = sqlEle.attribute("resultType");
        Attribute paramterTypeAttribute = sqlEle.attribute("paramterType");
        String sql = sqlEle.getTextTrim();
        if(idAttribute==null|| !StringUtil.hasText(idAttribute.getValue())){
            ExceptionBuilder.buildOrmException("id must config!");
        }
        mappedStatement.setId(idAttribute.getValue());
        if(paramterTypeAttribute!=null){
            mappedStatement.setParamterType(paramterTypeAttribute.getValue());
        }
        if(resultTypeAttribute!=null){
            mappedStatement.setResultType(resultTypeAttribute.getValue());
        }
        mappedStatement.setSql(sql);
        mappedStatement.setSqlCommandType(SqlCommandType.valueOf(sqlEle.getName().toUpperCase(Locale.ENGLISH)));
        return mappedStatement;
    }
}
