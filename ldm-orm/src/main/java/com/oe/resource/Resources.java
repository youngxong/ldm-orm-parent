package com.oe.resource;

import java.io.InputStream;

public class Resources {

    public static InputStream getResourceAsSteam(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return  resourceAsStream;
    }

}
