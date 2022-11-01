package com.text.analyzer.single.service.impl;

import com.text.analyzer.common.dto.SearchName;
import com.text.analyzer.pojo.LetterNumberEnum;
import com.text.analyzer.pojo.SearchType;
import com.text.analyzer.single.dto.LetterSearchDto;
import com.text.analyzer.single.dto.SingleWordSearchDto;
import com.text.analyzer.single.service.LetterSearchService;
import com.text.analyzer.single.service.NumberOfWordsService;
import com.text.analyzer.single.service.SingleWordAverageService;
import com.text.analyzer.single.service.WordSearchService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
    public SearchType getType() {
        return SearchType.SINGLE;
    }

    @Override
    public SingleWordSearchDto processSearches(List<String> searches) {
        List<LetterSearchDto> letterSearchDtos = letterSearchService.letterSearchDtos(searches);

        int averageNumberOfWords = numberOfWordsService.getAverageNumberOfWords(searches);
        int theMostWordInSearch = numberOfWordsService.getTheMostWordsInSearch(searches);
        int theLeastWords = numberOfWordsService.getTheLeastWordsInSearch(searches);
        int numberOfAllSearches = numberOfWordsService.getTotalNumberOfSearches(letterSearchDtos);
        BigDecimal averageNumberOfChars = singleWordAverageService.getAverageNumberOfChars(letterSearchDtos, numberOfAllSearches);

        return SingleWordSearchDto.builder()
                .averageNumberOfWords(averageNumberOfWords)
                .theMostWordInSearch(theMostWordInSearch)
                .theLeastWords(theLeastWords)
                .averageNumberOfChars(averageNumberOfChars)
                .averageNumberOfCharsPerWord(averageNumberOfChars)
                .name(SearchName.SINGLE_WORD_SEARCH.name())
                .numberOfSearches(numberOfAllSearches)
                .letterSearches(letterSearchDtos)
                .build();
    }

    public Map<LetterNumberEnum, Set<String>> getStringsToEnumNonConcurrent(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingBy(
                                LetterNumberEnum::fromString,
                                TreeMap::new,
                                Collectors.toSet()
                        )
                );
    }

    public Map<Integer, Set<String>> getStringsToIntConcurrent(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingByConcurrent(
                                String::length,
                                Collectors.toSet()
                        )
                );
    }

    public Map<LetterNumberEnum, Set<String>> getStringsToEnumConcurrent(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingByConcurrent(
                                LetterNumberEnum::fromString,
                                Collectors.toSet()
                        )
                );
    }
}
