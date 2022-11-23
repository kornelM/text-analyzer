package com.text.analyzer.search.process.multi.creator.service.impl;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.search.process.multi.creator.service.StatsService;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class StatsServiceImpl implements StatsService {

    @Override
    public BigDecimal averageNumberOfWords(List<WordSearchDto> wordSearchDtos) {
        BigDecimal averageNumberOfWords = BigDecimal.ZERO;
        int totalNumberOfAllSearches = wordSearchDtos.stream().map(WordSearchDto::getNumberOfSearches).mapToInt(Integer::intValue).sum();

        for (WordSearchDto dto : wordSearchDtos) {
            averageNumberOfWords = NumberUtils.calculateAverage(averageNumberOfWords, totalNumberOfAllSearches, dto::getAverageNumberOfCharsPerWord, dto::getNumberOfSearches);
        }
        return averageNumberOfWords;
    }

    @Override
    public int theMostWordsInSearch(List<WordSearchDto> wordSearchDtos) {
        return wordSearchDtos.stream()
                .map(WordSearchDto::getAverageNumberOfWordsPerSearch)
                .max(Comparator.naturalOrder())
                .map(BigDecimal::intValue)
                .orElse(-1);
    }

    @Override
    public int theLeastWordsInSearch(List<WordSearchDto> wordSearchDtos) {
        return wordSearchDtos.stream()
                .map(WordSearchDto::getAverageNumberOfWordsPerSearch)
                .sorted()
                .findFirst()
                .map(BigDecimal::intValue)
                .orElse(-1);
    }

    @Override
    public int totalNumberOfSearches(List<WordSearchDto> searches) {
        return searches.stream()
                .map(WordSearchDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
