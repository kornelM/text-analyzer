package com.text.analyzer.multi.service;

import com.text.analyzer.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.List;

public interface NumberOfWordsService {

    BigDecimal getAverageNumberOfWords(List<WordSearchDto> wordSearchDtos);

    int getTheMostWordsInSearch(List<WordSearchDto> wordSearchDtos);

    int getTheLeastWordsInSearch(List<WordSearchDto> wordSearchDtos);

    int getTotalNumberOfSearches(List<WordSearchDto> searches);
}
