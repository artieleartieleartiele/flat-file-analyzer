package com.hicx.solutions.service.parser;

import com.hicx.solutions.dto.FileStatistics;
import com.hicx.solutions.dto.PlainTextFileStatistics;
import com.hicx.solutions.service.FileStatisticsService;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hicx.solutions.constant.constants.*;

@NoArgsConstructor
public class PlainTextFileStatisticsService implements FileStatisticsService {
    @Override
    public FileStatistics process(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        int wordCount = 0;
        int dotCount = 0;

        List<String> dirtyWords = new ArrayList<>();
        List<String> cleanWords = new ArrayList<>();
        for (String line : lines) {
            String charPattern = CHARACTER_PATTERN_DOT;
            dotCount = getCharCount(dotCount, line, charPattern);

            line = StringUtils.replace(line, charPattern, REPLACE_NOTHING);
            String[] words = StringUtils.split(line.trim(), SEPARATOR_SPACE);
            wordCount = getWordCount(wordCount, words);
            dirtyWords.addAll(Arrays.asList(words));
        }

        for (String dirty : dirtyWords) {
            cleanWords.add(
                Arrays.stream(Arrays.stream(INVALID_CHARACTERS).toArray())
                .map(invalidChar -> StringUtils.replace(dirty, (String) invalidChar, REPLACE_NOTHING))
                .iterator().next());
        }

        FileStatistics statistics = new PlainTextFileStatistics();
        statistics.setFilename(file.getName());
        statistics.setDotCount(dotCount);
        statistics.setWordCount(wordCount);
        statistics.setPopularWords(getPopularWords(cleanWords));
        return statistics;
    }

    private int getCharCount(int dotCount, String line, String pattern) {
        int count = StringUtils.countMatches(line, pattern);
        return dotCount + count;
    }

    private int getWordCount(int wordCount, String[] words) {
        return wordCount+ words.length;
    }

    private Pair<List<String>, Integer> getPopularWords(List<String> lines) {
        Map<String,Integer> popWords = new HashMap<>();
        lines.stream()
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(s -> s))
            .forEach((k, v) -> popWords.put(k, v.size()));

        Optional<Map.Entry<String, Integer>> maxEntry = popWords.entrySet().stream().max(Map.Entry.comparingByValue());

        if (maxEntry.isPresent()) {
            Integer value = maxEntry.get().getValue();
            List<String> words = popWords.entrySet().stream()
                    .filter(entry -> value.equals(entry.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            return Pair.of(words, value);
        }
        return null;
    }
}
