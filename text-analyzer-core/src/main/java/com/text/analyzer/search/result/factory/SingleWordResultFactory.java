package com.text.analyzer.search.result.factory;

import com.text.analyzer.common.dto.WordSearchResultDto;
import com.text.analyzer.response.SingleWordSearchResult;
import com.text.analyzer.response.pojo.LetterSearch;
import com.text.analyzer.response.pojo.SearchName;
import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import com.text.analyzer.search.result.mapper.LetterSearchDtoMapper;
import com.text.analyzer.search.result.mapper.LetterSearchMapper;
import com.text.analyzer.search.result.service.SingleWordResultCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SingleWordResultFactory {

    public static final int THE_LEAST_WORDS_DEFAULT_VALUE = 1;
    public static final int THE_MOST_WORD_IN_SEARCH_DEFAULT_VALUE = 1;

    private final SingleWordResultCalculator singleWordResultCalculator;

    public SingleWordResultFactory() {
        this.singleWordResultCalculator = new SingleWordResultCalculator();
    }

    public SingleWordSearchResult getSingleWordSearchResult(int totalNumberOfSingleSearches, int totalNumberOfMultiSearches, List<SingleWordSearchDto> singleWordSearchDtos) {
        List<LetterSearchDto> allLetterSearches = LetterSearchDtoMapper.mapToLetterSearchDtos(singleWordSearchDtos);

        List<LetterSearch> letterSearches = LetterSearchMapper.mapToLetterSearches(allLetterSearches);
        BigDecimal percentOfAll = BigDecimal.valueOf(totalNumberOfSingleSearches).divide((BigDecimal.valueOf(totalNumberOfSingleSearches).add(BigDecimal.valueOf(totalNumberOfMultiSearches))), 4, RoundingMode.HALF_EVEN);
        int totalNumberOfSearches = singleWordSearchDtos.stream()
                .map(WordSearchResultDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();

//        BigDecimal averageNumberOfChars = BigDecimal.valueOf(singleWordSearchDtos.stream().map(WordSearchResultDto::getAverageNumberOfChars).mapToDouble(BigDecimal::doubleValue).average().orElse(-1));
        BigDecimal averageNumberOfWords = singleWordResultCalculator.averageNumberOfWords(singleWordSearchDtos, totalNumberOfSearches);
        BigDecimal averageNumberOfCharsPerWord = singleWordResultCalculator.averageNumberOfCharsPerWord(singleWordSearchDtos, totalNumberOfSearches);

        return SingleWordSearchResult.builder()
                .name(SearchName.SINGLE_WORD_SEARCH)
                .percentOfAll(percentOfAll)
                .numberOfSearches(totalNumberOfSingleSearches)
                .letterSearches(letterSearches)
                .theLeastWords(THE_LEAST_WORDS_DEFAULT_VALUE)
                .theMostWordInSearch(THE_MOST_WORD_IN_SEARCH_DEFAULT_VALUE)
//                .averageNumberOfChars(averageNumberOfChars)
                .averageNumberOfDigits(BigDecimal.valueOf(singleWordSearchDtos.stream().map(WordSearchResultDto::getAverageNumberOfDigits).mapToDouble(BigDecimal::doubleValue).average().orElse(-1)))
                .averageNumberOfWords(averageNumberOfWords)
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                .build();
    }
}
