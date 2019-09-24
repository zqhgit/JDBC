package com.qf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private static Properties pro = null;
    private static String dbConfigFile = "db.properties";
    static {
        pro = new Properties();
        InputStream is = null;
        try {
             is= DBConfig.class.getClassLoader().getResourceAsStream(dbConfigFile);
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getValues(String key){
        return pro.getProperty(key);
    }
}
