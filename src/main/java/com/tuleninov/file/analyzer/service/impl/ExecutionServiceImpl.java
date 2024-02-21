package com.tuleninov.file.analyzer.service.impl;

import com.tuleninov.file.analyzer.io.Printable;
import com.tuleninov.file.analyzer.io.Readable;
import com.tuleninov.file.analyzer.model.FileData;
import com.tuleninov.file.analyzer.service.Analyzable;
import com.tuleninov.file.analyzer.service.ExecutionService;

public class ExecutionServiceImpl implements ExecutionService {

    private final Readable readable;
    private final Analyzable analyzable;
    private final Printable printable;

    public ExecutionServiceImpl(Readable readable,
                                Analyzable analyzable,
                                Printable printable) {
        this.readable = readable;
        this.analyzable = analyzable;
        this.printable = printable;
    }

    @Override
    public void execute() {
        int[] numbers = readable.read();

        FileData fileData = analyzable.analyze(numbers);

        printable.print(fileData);
    }
}
