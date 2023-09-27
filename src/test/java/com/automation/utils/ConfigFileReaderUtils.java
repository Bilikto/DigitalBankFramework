package com.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReaderUtils {
    private static  Properties property;
    private static String configFilePath = "src/test/resources/config/config.properties";

    public static void initConfig() {
        try {
            property = new Properties();
            property.load(new FileInputStream(configFilePath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return property.getProperty(key);
    }

}
