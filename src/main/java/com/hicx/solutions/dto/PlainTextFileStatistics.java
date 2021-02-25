package com.hicx.solutions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Data
@NoArgsConstructor
public class PlainTextFileStatistics extends FileStatistics {
    public PlainTextFileStatistics(String filename, int wordCount, int dotCount, Pair<List<String>, Integer> popularWords) {
        super(filename, wordCount, dotCount, popularWords);
    }

    @Override
    public String toString() {
        return "PlainTextFileStatistics{" +
                "filename='" + super.getFilename() + '\'' +
                ", wordCount=" + super.getWordCount() +
                ", dotCount=" + super.getDotCount() +
                ", popularWords=" + super.getPopularWords() +
                '}';
    }
}
