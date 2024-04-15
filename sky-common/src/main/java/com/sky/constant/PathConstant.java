package com.sky.constant;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class PathConstant {

    public static final String server = "localhost:8080/";

    public static final String staticResourcePath = "/static/";

    public static final String resourcePath;

    static {
        try {
            resourcePath = ResourceUtils.getURL("classpath:static").getPath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
