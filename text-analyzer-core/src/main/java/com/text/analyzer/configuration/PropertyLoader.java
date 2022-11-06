package com.text.analyzer.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Objects.isNull;

public class PropertyLoader {

    private Properties conf;

    public String getProperty(ConfigProperty property) {
        if (isNull(this.conf)) {
            this.conf = loadProperties();
        }
        return this.conf.getProperty(property.getValue());
    }

    private Properties loadProperties() {
        Properties configuration = new Properties();
        try (InputStream inputStream = PropertyLoader.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            configuration.load(inputStream);
        } catch (IOException e) {
            System.out.println("Exception occurred while reading properties! REASON: " + e.getMessage());
        }
        return configuration;
    }
}
