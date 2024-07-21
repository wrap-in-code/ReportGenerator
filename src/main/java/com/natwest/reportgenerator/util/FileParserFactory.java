package com.natwest.reportgenerator.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileParserFactory {

    @Autowired
    private CsvFileParser csvFileParser;

    public FileParser getFileParser(String filePath) {
        if (filePath.endsWith(".csv")) {
            return csvFileParser;
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + filePath);
        }
    }
}
