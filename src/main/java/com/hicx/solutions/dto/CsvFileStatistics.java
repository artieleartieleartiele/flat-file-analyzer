package com.hicx.solutions.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
public class CsvFileStatistics extends FileStatistics {
    private int numRows;

    public CsvFileStatistics(String filename, int wordCount, int dotCount, Pair<List<String>, Integer> popularWords, int numRows) {
        super(filename, wordCount, dotCount, popularWords);
        this.numRows = numRows;
    }
}
