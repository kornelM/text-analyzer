package com.text.analyzer.data.configuration;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Objects.isNull;

@Slf4j
public class PropertyLoader {

    private Properties conf;

    public String getProperty(ResourceProperty property) {
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
            throw new RuntimeException("Exception occurred while reading properties! REASON: " + e.getMessage());
        }
        log.info("Loaded configuration: {}", configuration);
        return configuration;
    }
}
