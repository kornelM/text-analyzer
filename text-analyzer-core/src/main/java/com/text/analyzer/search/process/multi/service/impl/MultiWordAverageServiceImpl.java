package com.text.analyzer.search.process.multi.service.impl;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import com.text.analyzer.search.process.multi.service.MultiWordAverageService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class MultiWordAverageServiceImpl implements MultiWordAverageService {

    @Override
    public BigDecimal getAverageNumberOfWordsPerSearch(List<String> wordSearch) {
        BigDecimal numberOfSearches = BigDecimal.valueOf(wordSearch.size());
        int totalNumberOfWords = getTotalNumberOfWords(wordSearch);
        BigDecimal numberOfWords = BigDecimal.valueOf(totalNumberOfWords);
        return NumberUtils.divide(numberOfWords, numberOfSearches, 2, RoundingMode.UP);
    }

    @Override
    public BigDecimal getAverageNumberOfCharsPerWord(List<String> wordSearch) {
        int totalNumberOfCharsInSearches = wordSearch.stream()
                .map(String::trim)
                .map(s -> s.replace(" ", ""))
                .map(String::length)
                .mapToInt(Integer::intValue)
                .sum();
        BigDecimal numberOfCharsInSearches = BigDecimal.valueOf(totalNumberOfCharsInSearches);
        int totalNumberOfWords = getTotalNumberOfWords(wordSearch);
        BigDecimal numberOfWords = BigDecimal.valueOf(totalNumberOfWords);
        return NumberUtils.divide(numberOfCharsInSearches, numberOfWords);
    }

    @Override
    public BigDecimal getAverageNumberOfWordsPerSearch(List<WordSearchDto> wordSearchDtos, List<String> searches) {
        return BigDecimal.valueOf(wordSearchDtos.stream()
                .map(WordSearchDto::getAverageNumberOfCharsPerWord)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(-1));
    }

    private static int getTotalNumberOfWords(List<String> wordSearch) {
        return wordSearch.stream()
                .map(String::trim)
                .map(s -> s.split(" "))
                .map(a -> a.length)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
