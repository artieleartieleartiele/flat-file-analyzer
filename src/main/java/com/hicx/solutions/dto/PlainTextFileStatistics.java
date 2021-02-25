package com.hicx.solutions.dto;

import lombok.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
public class PlainTextFileStatistics extends FileStatistics {
    public PlainTextFileStatistics(String filename, int wordCount, int dotCount, Pair<List<String>, Integer> popularWords) {
        super(filename, wordCount, dotCount, popularWords);
    }
}
