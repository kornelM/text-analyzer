package com.text.analyzer.search.process.multi.service.impl;

import com.text.analyzer.common.dto.SearchResultDto;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import com.text.analyzer.search.process.multi.service.NumberOfWordsService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

class NumberOfWordsServiceImpl implements NumberOfWordsService {

    @Override
    public BigDecimal getAverageNumberOfWords(List<WordSearchDto> wordSearchDtos) {
        return BigDecimal.valueOf(wordSearchDtos.stream()
                .map(WordSearchDto::getAverageNumberOfCharsPerWord)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(-1));
    }

    @Override
    public int getTheMostWordsInSearch(List<WordSearchDto> wordSearchDtos) {
        return wordSearchDtos.stream()
                .map(WordSearchDto::getAverageNumberOfWordsPerSearch)
                .max(Comparator.naturalOrder())
                .map(BigDecimal::intValue)
                .orElse(-1);
    }

    @Override
    public int getTheLeastWordsInSearch(List<WordSearchDto> wordSearchDtos) {
        return wordSearchDtos.stream()
                .map(WordSearchDto::getAverageNumberOfWordsPerSearch)
                .sorted()
                .findFirst()
                .map(BigDecimal::intValue)
                .orElse(-1);
    }

    @Override
    public int getTotalNumberOfSearches(List<WordSearchDto> searches) {
        return searches.stream()
                .map(SearchResultDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
