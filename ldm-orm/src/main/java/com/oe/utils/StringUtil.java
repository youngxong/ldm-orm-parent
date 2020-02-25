package com.oe.utils;

import com.oe.exception.ExceptionBuilder;

import java.util.Locale;

public class StringUtil {

    public static boolean hasText(String text){
        boolean flag=true;
        if(text==null||text.length()==0||text.trim().length()==0){
            flag=false;
        }
        return flag;
    }

    public static boolean compareIgnoreCase(String source,String target){
        boolean flag=false;
        if(!hasText(source)||!hasText(target)){
            ExceptionBuilder.buildOrmException("参数输入异常！");
        }
        if(source.trim().toUpperCase(Locale.ENGLISH).equals(target.trim().toUpperCase(Locale.ENGLISH))){
            flag=true;
        }
        return flag;
    }
}
