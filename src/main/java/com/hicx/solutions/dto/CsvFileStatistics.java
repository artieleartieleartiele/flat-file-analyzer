package com.hicx.solutions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Data
@NoArgsConstructor
public class CsvFileStatistics extends FileStatistics {
    private int numRows;

    public CsvFileStatistics(String filename, int wordCount, int dotCount, Pair<List<String>, Integer> popularWords, int numRows) {
        super(filename, wordCount, dotCount, popularWords);
        this.numRows = numRows;
    }

    @Override
    public String toString() {
        return "PlainTextFileStatistics{" +
                "filename='" + super.getFilename() + '\'' +
                ", wordCount=" + super.getWordCount() +
                ", dotCount=" + super.getDotCount() +
                ", popularWords=" + super.getPopularWords() +
                ", numRows=" + this.getNumRows() +
                '}';
    }
}
