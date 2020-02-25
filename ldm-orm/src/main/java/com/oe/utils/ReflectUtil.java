package com.oe.utils;

import com.oe.exception.ExceptionBuilder;

public class ReflectUtil {

    public static Object getInstanceByClassName(String className){
        Class clazz=null;
        try {
            clazz =Class.forName(className);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            ExceptionBuilder.buildOrmException("类找不到",e);
        } catch (IllegalAccessException e) {
            ExceptionBuilder.buildOrmException("反射获取实例异常",e);
        } catch (InstantiationException e) {
            ExceptionBuilder.buildOrmException("反射获取实例异常",e);
        }
        return null;
    }
}
