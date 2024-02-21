package com.tuleninov.file.analyzer.config.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesConfig {

    private static final Logger log = LoggerFactory.getLogger(PropertiesConfig.class);

    public Properties getProperties(String fileName) {
        try(InputStream in = getClass().getClassLoader().getResourceAsStream(fileName)) {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (IOException e) {
            log.info("UncheckedIOException in the PropertiesConfig.class.");
            throw new UncheckedIOException(e);
        }
    }
}
