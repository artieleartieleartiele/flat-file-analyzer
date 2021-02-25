package com.hicx.solutions.service.parser;

import com.hicx.solutions.service.FileStatisticsService;

import java.io.File;

public class FileParserFactory {
    private static final String TYPE_PLAINTEXT = ".txt";
    private static final String TYPE_CSV = ".csv";

    public FileStatisticsService getFileStatisticsService(File file) {
        String filename = file.getName().toLowerCase();
        if (filename.endsWith(TYPE_PLAINTEXT)) {
            return new PlainTextFileStatisticsService();
        } else if (filename.endsWith(TYPE_CSV)) {
            return new CsvFileStatisticsService();
        } else {
            System.out.println("Unsupported File Format for: " + file.getName());
            return null;
        }
    }
}
