package com.hicx.solutions.service;

import com.hicx.solutions.dto.FileStatistics;

import java.io.File;
import java.io.IOException;

public interface FileStatisticsService {
    FileStatistics process(File file) throws IOException;
}
