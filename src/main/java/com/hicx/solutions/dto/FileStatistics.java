package com.hicx.solutions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * This statistics base class is extended by other classes that may expose other data not applicable to other file formats
 * e.g. we may want to get number of rows in csv file
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileStatistics {
    private String filename;
    private int wordCount;
    private int dotCount;
    private Pair<List<String>, Integer> popularWords;
}
