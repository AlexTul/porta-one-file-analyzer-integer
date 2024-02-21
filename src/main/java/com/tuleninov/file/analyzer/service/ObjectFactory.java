package com.tuleninov.file.analyzer.service;

public interface ObjectFactory {

    <T> T createObject(Class<T> clazz);

}
