package com.hicx.solutions.service.parser;

import com.hicx.solutions.dto.FileStatistics;
import com.hicx.solutions.dto.PlainTextFileStatistics;
import com.hicx.solutions.service.FileStatisticsService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class PlainTextFileStatisticsService implements FileStatisticsService {
    private static final String SEPARATOR_SPACE = " ";
    private static final String REPLACE_NOTHING = "";

    @Override
    public FileStatistics process(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        int wordCount = 0;
        int dotCount = 0;

        List<String> wordDump = new ArrayList<>();
        for (String line : lines) {
            String charPattern = ".";
            dotCount = getCharCount(dotCount, line, charPattern);

            line = StringUtils.replace(line, charPattern, REPLACE_NOTHING);
            String[] words = StringUtils.split(line.trim(), SEPARATOR_SPACE);
            wordCount = getWordCount(wordCount, words);
            wordDump.addAll(Arrays.asList(words));
        }
        FileStatistics statistics = new PlainTextFileStatistics();
        statistics.setFilename(file.getName());
        statistics.setDotCount(dotCount);
        statistics.setWordCount(wordCount);
        statistics.setPopularWords(getPopularWords(wordDump));
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
        Set<String> unique = lines.stream().map(String::toLowerCase).collect(Collectors.toSet());
        String joined = lines.stream().map(String::toLowerCase).collect(Collectors.joining());

        unique.forEach(word -> {
            int count = StringUtils.countMatches(joined, word);
            popWords.put(word,count);
        });

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
