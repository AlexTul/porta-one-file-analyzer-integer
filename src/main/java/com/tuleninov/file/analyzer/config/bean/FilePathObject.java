package com.tuleninov.file.analyzer.config.bean;

import com.tuleninov.file.analyzer.config.properties.PropertiesConfig;
import com.tuleninov.file.analyzer.model.FilePaths;
import com.tuleninov.file.analyzer.config.properties.PropertiesConstants;

public class FilePathObject {

    private final PropertiesConfig propertiesConfig;

    public FilePathObject(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }

    public FilePaths filePaths() {
        var properties = propertiesConfig.getProperties(PropertiesConstants.FILE_PATH_PROPERTIES);
        var source = properties.getProperty(PropertiesConstants.FILE_PATH_SOURCE);
        var result = properties.getProperty(PropertiesConstants.FILE_PATH_RESULT);

        return new FilePaths(source, result);
    }
}
