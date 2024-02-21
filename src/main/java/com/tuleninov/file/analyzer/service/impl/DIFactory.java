package com.tuleninov.file.analyzer.service.impl;

import com.tuleninov.file.analyzer.config.bean.FilePathObject;
import com.tuleninov.file.analyzer.config.properties.PropertiesConfig;
import com.tuleninov.file.analyzer.io.Printable;
import com.tuleninov.file.analyzer.io.Readable;
import com.tuleninov.file.analyzer.io.impl.FilePrintable;
import com.tuleninov.file.analyzer.io.impl.FileReadable;
import com.tuleninov.file.analyzer.model.FilePaths;
import com.tuleninov.file.analyzer.service.Analyzable;
import com.tuleninov.file.analyzer.service.ObjectFactory;
import com.tuleninov.file.analyzer.service.ExecutionService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DIFactory implements ObjectFactory {

    private static DIFactory instance;

    private final Map<Class<?>, Object> cache = new ConcurrentHashMap<>();

    private DIFactory() {
        if (instance != null) {
            throw new IllegalStateException("Singleton instance already exists. Use getInstance() method.");
        }
    }

    public static DIFactory getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (DIFactory.class) {
            if (instance == null) {
                instance = new DIFactory();
            }
        }
        return instance;
    }

    @Override
    public <T> T createObject(Class<T> clazz) {
        Object object = cache.get(clazz);

        if (object != null) {
            return (T) object;
        }

        if (clazz == PropertiesConfig.class) {
            object = new PropertiesConfig();
        } else if (clazz == FilePaths.class) {
            PropertiesConfig propertiesConfig = createObject(PropertiesConfig.class);
            object = new FilePathObject(propertiesConfig).filePaths();
        } else if (clazz == Readable.class) {
            FilePaths filePaths = createObject(FilePaths.class);
            object = new FileReadable(filePaths);
        } else if (clazz == Analyzable.class) {
            object = new FileAnalyzable();
        } else if (clazz == Printable.class) {
            FilePaths filePaths = createObject(FilePaths.class);
            object = new FilePrintable(filePaths);
        } else if (clazz == ExecutionService.class) {
            Readable readable = createObject(Readable.class);
            Analyzable analyzable = createObject(Analyzable.class);
            Printable printable = createObject(Printable.class);
            object = new ExecutionServiceImpl(readable, analyzable, printable);
        }

        if (object != null) {
            cache.put(clazz, object);
            return (T) object;
        } else {
            throw new RuntimeException("Failed to create an instance of " + clazz.getName());
        }
    }
}
