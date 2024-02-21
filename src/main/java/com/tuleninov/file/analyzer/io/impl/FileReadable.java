package com.tuleninov.file.analyzer.io.impl;

import com.tuleninov.file.analyzer.io.Readable;
import com.tuleninov.file.analyzer.model.FilePaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class FileReadable implements Readable {

    private static final Logger log = LoggerFactory.getLogger(FileReadable.class);

    private final FilePaths filePaths;

    public FileReadable(FilePaths filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public int[] read() {
        List<Integer> numbers = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePaths.filePathSource()))) {
            String line;
            int number;
            while ((line = reader.readLine()) != null) {
                number = Integer.parseInt(line);
                numbers.add(number);
            }
        } catch (IOException e) {
            log.info("UncheckedIOException in the PropertiesConfig.class.");
            throw new UncheckedIOException(e);
        }

        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
