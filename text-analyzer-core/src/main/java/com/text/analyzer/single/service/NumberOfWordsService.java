package com.text.analyzer.single.service;

import com.text.analyzer.single.dto.LetterSearchDto;

import java.util.List;

public interface NumberOfWordsService {

    int getAverageNumberOfWords(List<String> searches);

    int getTheMostWordsInSearch(List<String> searches);

    int getTheLeastWordsInSearch(List<String> searches);

    int getTotalNumberOfSearches(List<LetterSearchDto> searches);
}
