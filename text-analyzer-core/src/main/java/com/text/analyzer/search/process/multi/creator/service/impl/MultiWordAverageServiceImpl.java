package com.text.analyzer.search.process.multi.creator.service.impl;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.common.utils.StringUtils;
import com.text.analyzer.search.process.multi.creator.service.MultiWordAverageService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MultiWordAverageServiceImpl implements MultiWordAverageService {

    @Override
    public BigDecimal averageNumberOfWordsPerSearch(List<String> wordSearch) {
        BigDecimal numberOfSearches = BigDecimal.valueOf(wordSearch.size());
        BigDecimal numberOfWords = getTotalNumberOfWords(wordSearch);
        return NumberUtils.divide(numberOfWords, numberOfSearches, 2, RoundingMode.UP);
    }

    @Override
    public BigDecimal averageNumberOfCharsPerWord(List<String> wordSearch) {
        int totalNumberOfCharsInSearches = wordSearch.stream()
                .map(String::trim)
                .map(s -> s.replace(StringUtils.SPACE, StringUtils.EMPTY))
                .map(String::length)
                .mapToInt(Integer::intValue)
                .sum();
        BigDecimal numberOfCharsInSearches = BigDecimal.valueOf(totalNumberOfCharsInSearches);
        BigDecimal numberOfWords = getTotalNumberOfWords(wordSearch);
        return NumberUtils.divide(numberOfCharsInSearches, numberOfWords);
    }

    private static BigDecimal getTotalNumberOfWords(List<String> wordSearch) {
        return BigDecimal.valueOf(wordSearch.stream()
                .map(String::trim)
                .map(s -> s.split(StringUtils.SPACE))
                .map(a -> a.length)
                .mapToInt(Integer::intValue)
                .sum()
        );
    }
}
