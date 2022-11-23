package com.text.analyzer.search.process.single.processor;

import com.text.analyzer.response.pojo.SearchName;
import com.text.analyzer.search.process.WordSearchProcessor;
import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import com.text.analyzer.search.process.single.service.LetterSearchService;
import com.text.analyzer.search.process.single.service.NumberOfWordsService;
import com.text.analyzer.search.process.single.service.SingleWordAverageService;
import com.text.analyzer.search.process.single.service.impl.NumberOfWordsServiceImpl;
import com.text.analyzer.search.process.single.service.impl.SingleWordAverageServiceImpl;
import com.text.analyzer.search.process.single.service.impl.SingleWordSearchServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class SingleWordSearchProcessor implements WordSearchProcessor<SingleWordSearchDto> {

    private final LetterSearchService letterSearchService;
    private final NumberOfWordsService numberOfWordsService;
    private final SingleWordAverageService singleWordAverageService;

    public SingleWordSearchProcessor() {
        this.letterSearchService = new SingleWordSearchServiceImpl();
        this.numberOfWordsService = new NumberOfWordsServiceImpl();
        this.singleWordAverageService = new SingleWordAverageServiceImpl();
    }

    @Override
    public SingleWordSearchDto processSearches(List<String> searches) {
        List<LetterSearchDto> singleWordSearches = letterSearchService.createSingleWordSearches(searches);

        int averageNumberOfWords = numberOfWordsService.averageNumberOfWords(searches);
        int theMostWordInSearch = numberOfWordsService.theMostWordsInSearch(searches);
        int theLeastWords = numberOfWordsService.theLeastWordsInSearch(searches);
        int numberOfAllSearches = numberOfWordsService.totalNumberOfSearches(singleWordSearches);
        BigDecimal averageNumberOfChars = singleWordAverageService.averageNumberOfChars(singleWordSearches, numberOfAllSearches);
        BigDecimal averageNumberOfDigits = singleWordAverageService.averageNumberOfDigits(singleWordSearches, numberOfAllSearches);

        return SingleWordSearchDto.builder()
                .averageNumberOfWords(averageNumberOfWords)
                .theMostWordInSearch(theMostWordInSearch)
                .theLeastWords(theLeastWords)
                .averageNumberOfCharsPerWord(averageNumberOfChars)
                .averageNumberOfDigits(averageNumberOfDigits)
                .name(SearchName.SINGLE_WORD_SEARCH)
                .numberOfSearches(numberOfAllSearches)
                .letterSearches(singleWordSearches)
                .build();
    }
}
