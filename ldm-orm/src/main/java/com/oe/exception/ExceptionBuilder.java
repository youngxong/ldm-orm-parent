package com.oe.exception;

import com.oe.log.BaseLog;

public class ExceptionBuilder extends BaseLog{

    public static void buildOrmException(String msg,Exception e){
        logError(msg,e);
        throw new OrmException(msg);
    }

    public static void buildOrmException(String  msg){
        logError(msg);
        throw new OrmException(msg);
    }
}
