package com.text.analyzer.search.result.factory;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.response.MultiWordSearchResult;
import com.text.analyzer.response.pojo.SearchName;
import com.text.analyzer.response.pojo.WordSearch;
import com.text.analyzer.response.pojo.WordSearchEnum;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import com.text.analyzer.search.result.mapper.WordSearchDtoMapper;
import com.text.analyzer.search.result.mapper.WordSearchMapper;
import com.text.analyzer.search.result.service.MultiWordResultCalculator;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultiWordResultFactory {

    private final MultiWordResultCalculator multiWordResultCalculator;

    public MultiWordResultFactory() {
        this.multiWordResultCalculator = new MultiWordResultCalculator();
    }

    private static int getTotalNumberOfSearches(List<MultiWordSearchDto> multiWordSearchDtos) {
        return multiWordSearchDtos.stream()
                .map(MultiWordSearchDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static List<String> getPotentialSqlInjections(List<WordSearchDto> allWordSearches) {
        return allWordSearches.stream()
                .map(WordSearchDto::getPotentialSqlInjections)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public MultiWordSearchResult getMultiWordSearchResult(int totalNumberOfSingleSearches, int totalNumberOfMultiSearches, List<SingleWordSearchDto> singleWordSearchDtos, List<MultiWordSearchDto> multiWordSearchDtos) {
        BigDecimal percentOfAll = getPercentOfAll(totalNumberOfSingleSearches, totalNumberOfMultiSearches);
        List<WordSearchDto> allWordSearches = WordSearchDtoMapper.mapToWordSearchDtos(multiWordSearchDtos);
        List<WordSearch> wordsSearches = WordSearchMapper.mapToWordSearches(allWordSearches);
        Integer leastWords = getLeastWords(allWordSearches);
        Integer lengthOfLongestSearchedSentence = getLengthOfLongestSearchedSentence(allWordSearches);

        int totalNumberOfSearches = getTotalNumberOfSearches(multiWordSearchDtos);
        BigDecimal averageNumberOfWords = multiWordResultCalculator.averageNumberOfWords(multiWordSearchDtos, totalNumberOfSearches);
        BigDecimal averageNumberOfCharsPerWord = multiWordResultCalculator.averageNumberOfCharsPerWord(multiWordSearchDtos, totalNumberOfSearches);

        List<String> potentialSqlInjections = getPotentialSqlInjections(allWordSearches);

        return MultiWordSearchResult.builder()
                .name(SearchName.MULTI_WORD_SEARCH)
                .percentOfAll(percentOfAll)
                .numberOfSearches(totalNumberOfMultiSearches)
                .wordsSearches(wordsSearches)
                .theLeastWords(leastWords)
                .theMostWordInSearch(lengthOfLongestSearchedSentence)
                .averageNumberOfWords(averageNumberOfWords)
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                .potentialSqlInjections(potentialSqlInjections)
                .build();
    }

    private BigDecimal getPercentOfAll(int totalNumberOfSingleSearches, int totalNumberOfMultiSearches) {
        BigDecimal totalNumberOfMultiSearchesBigDecimal = BigDecimal.valueOf(totalNumberOfMultiSearches);
        BigDecimal totalNumberOfSearches = BigDecimal.valueOf(totalNumberOfSingleSearches).add(totalNumberOfMultiSearchesBigDecimal);
        return NumberUtils.divide(totalNumberOfMultiSearchesBigDecimal, totalNumberOfSearches);
    }

    private Integer getLeastWords(List<WordSearchDto> allWordSearches) {
        return allWordSearches.stream()
                .map(WordSearchDto::getName)
                .map(WordSearchEnum::getWordLength)
                .min(Comparator.naturalOrder())
                .orElse(-1);
    }

    private Integer getLengthOfLongestSearchedSentence(List<WordSearchDto> allWordSearches) {
        return allWordSearches.stream()
                .map(WordSearchDto::getName)
                .map(WordSearchEnum::getWordLength)
                .max(Comparator.naturalOrder())
                .orElse(-1);
    }
}
