package com.text.analyzer.search.process.multi.service;

import com.text.analyzer.search.process.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.List;

public interface NumberOfWordsService {

    BigDecimal getAverageNumberOfWords(List<WordSearchDto> wordSearchDtos);

    int getTheMostWordsInSearch(List<WordSearchDto> wordSearchDtos);

    int getTheLeastWordsInSearch(List<WordSearchDto> wordSearchDtos);

    int getTotalNumberOfSearches(List<WordSearchDto> searches);
}
