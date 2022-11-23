package com.text.analyzer.search.process.multi.creator.service;

import com.text.analyzer.search.process.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.List;

public interface StatsService {

    BigDecimal averageNumberOfWords(List<WordSearchDto> wordSearchDtos);

    int theMostWordsInSearch(List<WordSearchDto> wordSearchDtos);

    int theLeastWordsInSearch(List<WordSearchDto> wordSearchDtos);

    int totalNumberOfSearches(List<WordSearchDto> searches);
}
