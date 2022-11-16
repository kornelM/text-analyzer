package com.text.analyzer.search.process.single.service.impl;

import com.text.analyzer.common.service.WordSearchService;
import com.text.analyzer.response.pojo.SearchName;
import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import com.text.analyzer.search.process.single.service.LetterSearchService;
import com.text.analyzer.search.process.single.service.NumberOfWordsService;
import com.text.analyzer.search.process.single.service.SingleWordAverageService;

import java.math.BigDecimal;
import java.util.List;

public class SingleWordSearchServiceImpl implements WordSearchService<SingleWordSearchDto> {

    private final LetterSearchService letterSearchService;
    private final NumberOfWordsService numberOfWordsService;
    private final SingleWordAverageService singleWordAverageService;

    public SingleWordSearchServiceImpl() {
        this.letterSearchService = new LetterSearchServiceImpl();
        this.numberOfWordsService = new NumberOfWordsServiceImpl();
        this.singleWordAverageService = new SingleWordAverageServiceImpl();
    }

    @Override
    public SingleWordSearchDto processSearches(List<String> searches) {
        List<LetterSearchDto> letterSearchDtos = letterSearchService.letterSearchDtos(searches);

        int averageNumberOfWords = numberOfWordsService.getAverageNumberOfWords(searches);
        int theMostWordInSearch = numberOfWordsService.getTheMostWordsInSearch(searches);
        int theLeastWords = numberOfWordsService.getTheLeastWordsInSearch(searches);
        int numberOfAllSearches = numberOfWordsService.getTotalNumberOfSearches(letterSearchDtos);
        BigDecimal averageNumberOfChars = singleWordAverageService.getAverageNumberOfChars(letterSearchDtos, numberOfAllSearches);
        BigDecimal averageNumberOfDigits = singleWordAverageService.getAverageNumberOfDigits(letterSearchDtos, numberOfAllSearches);

        return SingleWordSearchDto.builder()
                .averageNumberOfWords(averageNumberOfWords)
                .theMostWordInSearch(theMostWordInSearch)
                .theLeastWords(theLeastWords)
                .averageNumberOfCharsPerWord(averageNumberOfChars)
                .averageNumberOfDigits(averageNumberOfDigits)
                .name(SearchName.SINGLE_WORD_SEARCH)
                .numberOfSearches(numberOfAllSearches)
                .letterSearches(letterSearchDtos)
                .build();
    }
}
