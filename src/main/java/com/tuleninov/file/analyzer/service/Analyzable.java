package com.tuleninov.file.analyzer.service;

import com.tuleninov.file.analyzer.model.FileData;

public interface Analyzable {

    FileData analyze(int[] numbers);

}
