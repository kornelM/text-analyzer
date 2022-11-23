package com.text.analyzer.search.process.single.service;

import com.text.analyzer.search.process.single.dto.LetterSearchDto;

import java.util.List;

public interface NumberOfWordsService {

    int averageNumberOfWords(List<String> searches);

    int theMostWordsInSearch(List<String> searches);

    int theLeastWordsInSearch(List<String> searches);

    int totalNumberOfSearches(List<LetterSearchDto> searches);
}
