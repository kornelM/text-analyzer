package com.text.analyzer.multi.service.impl;

import com.text.analyzer.common.dto.SearchName;
import com.text.analyzer.multi.dto.MultiWordSearchDto;
import com.text.analyzer.multi.dto.WordSearchDto;
import com.text.analyzer.multi.service.MultiWordAverageService;
import com.text.analyzer.multi.service.NumberOfWordsService;
import com.text.analyzer.multi.service.WordsSearchService;
import com.text.analyzer.pojo.SearchType;
import com.text.analyzer.single.service.WordSearchService;

import java.math.BigDecimal;
import java.util.List;

public class MultiWordSearchServiceImpl implements WordSearchService<MultiWordSearchDto> {

    private final WordsSearchService wordsSearchService;
    private final NumberOfWordsService numberOfWordsService;
    private final MultiWordAverageService multiWordAverageService;

    public MultiWordSearchServiceImpl() {
        this.wordsSearchService = new WordsSearchServiceImpl();
        this.numberOfWordsService = new NumberOfWordsServiceImpl();
        this.multiWordAverageService = new MultiWordAverageServiceImpl();
    }

    @Override
    public SearchType getType() {
        return SearchType.MULTI;
    }

    @Override
    public MultiWordSearchDto processSearches(List<String> searches) {
        List<WordSearchDto> wordSearchDtos = wordsSearchService.wordSearchDtos(searches);

        BigDecimal averageNumberOfWords = numberOfWordsService.getAverageNumberOfWords(wordSearchDtos);
        int theMostWordInSearch = numberOfWordsService.getTheMostWordsInSearch(wordSearchDtos);
        int theLeastWords = numberOfWordsService.getTheLeastWordsInSearch(wordSearchDtos);
        int numberOfAllSearches = numberOfWordsService.getTotalNumberOfSearches(wordSearchDtos);
        BigDecimal averageNumberOfChars = multiWordAverageService.getAverageNumberOfWordsPerSearch(wordSearchDtos, searches);

        return MultiWordSearchDto.builder()
                .averageNumberOfWords(averageNumberOfWords)
                .theMostWordInSearch(theMostWordInSearch)
                .theLeastWords(theLeastWords)
                .averageNumberOfChars(averageNumberOfChars)
//                .averageNumberOfCharsPerWord(averageNumberOfChars)
                .name(SearchName.MULTI_WORD_SEARCH.name())
                .numberOfSearches(numberOfAllSearches)
                .wordSearches(wordSearchDtos)
                .build();
    }
}
