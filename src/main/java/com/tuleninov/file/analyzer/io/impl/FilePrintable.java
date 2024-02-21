package com.tuleninov.file.analyzer.io.impl;

import com.tuleninov.file.analyzer.io.Printable;
import com.tuleninov.file.analyzer.model.FileData;
import com.tuleninov.file.analyzer.model.FilePaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

public class FilePrintable implements Printable {

    private static final Logger log = LoggerFactory.getLogger(FilePrintable.class);

    private final FilePaths filePaths;

    public FilePrintable(FilePaths filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public void print(FileData fileData) {
        String output = String.format("Max number: %d, min number:  %d, median number: %f, average number:  %f, " +
                        "increasing sequence: %s, decreasing sequence: %s.",
                fileData.maxNumber(), fileData.minNumber(),
                fileData.medianNumber(), fileData.averageNumber(),
                Arrays.toString(fileData.increasingSequence()),
                Arrays.toString(fileData.decreasingSequence()));

        writeToFileResult(output);
    }

    private void writeToFileResult(String output) {
        try (Writer writer = new BufferedWriter(new FileWriter(filePaths.filePathResult()))) {
            writer.write(output);
        } catch (IOException e) {
            log.info("UncheckedIOException in the PropertiesConfig.class.");
            throw new UncheckedIOException(e);
        }
    }
}
