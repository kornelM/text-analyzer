package com.text.analyzer.search.process.single.service.impl;

import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import com.text.analyzer.search.process.single.service.NumberOfWordsService;

import java.util.List;

public class NumberOfWordsServiceImpl implements NumberOfWordsService {

    private static final int AVERAGE_NUMBER_OF_WORDS = 1;
    private static final int THE_MOST_WORDS_IN_SEARCH = 1;
    private static final int THE_LEAST_WORDS_IN_SEARCH = 1;

    @Override
    public int averageNumberOfWords(List<String> searches) {
        return AVERAGE_NUMBER_OF_WORDS;
    }

    @Override
    public int theMostWordsInSearch(List<String> searches) {
        return THE_MOST_WORDS_IN_SEARCH;
    }

    @Override
    public int theLeastWordsInSearch(List<String> searches) {
        return THE_LEAST_WORDS_IN_SEARCH;
    }

    @Override
    public int totalNumberOfSearches(List<LetterSearchDto> searches) {
        return searches.stream()
                .map(LetterSearchDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
