package com.company.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/7/26 20:22
 */
public class PropertiesUtil {
    private Properties properties;
    private static Logger logger;
    public PropertiesUtil(String path) {
        properties = new Properties();
        //通过字节码获得这个文件的IO流
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public String getProperties(String key){

        return properties.getProperty(key);
    }
}
