package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static String getProperty(String key) {
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream file = new FileInputStream("src/test/resources/config.properties");
                properties.load(file);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao ler o arquivo config.properties", e);
            }
        }
        return properties.getProperty(key);
    }
}
