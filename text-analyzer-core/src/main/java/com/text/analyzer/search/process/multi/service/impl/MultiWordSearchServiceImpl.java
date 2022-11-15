package com.text.analyzer.search.process.multi.service.impl;

import com.text.analyzer.common.service.WordSearchService;
import com.text.analyzer.pojo.SearchType;
import com.text.analyzer.response.pojo.SearchName;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import com.text.analyzer.search.process.multi.service.MultiWordAverageService;
import com.text.analyzer.search.process.multi.service.NumberOfWordsService;
import com.text.analyzer.search.process.multi.service.WordsSearchService;

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
        BigDecimal averageNumberOfCharsPerWord = multiWordAverageService.getAverageNumberOfCharsPerWord(searches);

        return MultiWordSearchDto.builder()
                .name(SearchName.MULTI_WORD_SEARCH)
                .averageNumberOfWords(averageNumberOfWords.intValue())
                .theMostWordInSearch(theMostWordInSearch)
                .theLeastWords(theLeastWords)
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                .numberOfSearches(numberOfAllSearches)
                .wordSearches(wordSearchDtos)
                .build();
    }
}
