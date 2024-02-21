package com.tuleninov.file.analyzer;

import com.tuleninov.file.analyzer.config.properties.PropertiesConfig;
import com.tuleninov.file.analyzer.service.ExecutionService;
import com.tuleninov.file.analyzer.service.impl.DIFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileAnalyzerApp {

    private static final Logger log = LoggerFactory.getLogger(PropertiesConfig.class);

    public static void main(String[] args) {
        System.exit(new FileAnalyzerApp().run());
    }

    public int run() {
        DIFactory diFactory = DIFactory.getInstance();
        ExecutionService executionService = diFactory.createObject(ExecutionService.class);

        try {
            executionService.execute();
        } catch (Exception e) {
            log.info("Exception error: " + e.getMessage());
            return -1;
        }

        return 0;
    }
}
