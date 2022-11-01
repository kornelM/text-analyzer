package com.text.analyzer.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties() throws IOException {
        Properties configuration = new Properties();
        try (InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            configuration.load(inputStream);
        } catch (IOException e) {
            System.out.println("Exception occurred while reading properties! REASON: " + e.getMessage());
            throw e;
        }
        return configuration;
    }
}
