package com.hicx.solutions.service.parser;

import com.hicx.solutions.dto.CsvFileStatistics;
import com.hicx.solutions.dto.FileStatistics;
import com.hicx.solutions.service.FileStatisticsService;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * No implementation, just to show what it would look like if additional file formats are catered
 * */
@NoArgsConstructor
public class CsvFileStatisticsService implements FileStatisticsService {
    @Override
    public FileStatistics process(File file) {
        FileStatistics statistics = new CsvFileStatistics();
        statistics.setFilename(file.getName());
        return statistics;
    }
}
