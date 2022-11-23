package com.text.analyzer.search.result.mapper;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.response.pojo.WordSearchEnum;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@UtilityClass
public class WordSearchDtoMapper {

    private static final int FIRST_ELEMENT_INDEX = 0;

    public static List<WordSearchDto> mapToWordSearchDtos(List<MultiWordSearchDto> multiWordSearchDtos) {
        List<WordSearchDto> allWordSearches = multiWordSearchDtos.stream()
                .flatMap(s -> s.getWordSearches().stream())
                .collect(Collectors.toList());
        Map<WordSearchEnum, List<WordSearchDto>> groupedWordSearches = allWordSearches.stream().collect(Collectors.groupingBy(WordSearchDto::getName));

        int sum = groupedWordSearches.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(WordSearchDto::getNumberOfSearches)
                .sum();

        return groupedWordSearches.values().stream()
                .map(a -> WordSearchDtoMapper.mergeWords(a, sum))
                .collect(Collectors.toList());
    }

    private static WordSearchDto mergeWords(List<WordSearchDto> wordSearchDtos, int totalNumberOfMultiWordSearches) {
        BigDecimal averageNumberOfCharsPerWord = BigDecimal.ZERO;
        BigDecimal averageNumberOfWordsPerSearch = BigDecimal.ZERO;
        BigDecimal percentOfAllMultiWordSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        Set<String> sqlInjections = new HashSet<>();
        int totalNumberOfSearches = wordSearchDtos.stream().map(WordSearchDto::getNumberOfSearches).map(BigDecimal::valueOf).mapToInt(BigDecimal::intValue).sum();

        for (WordSearchDto dto : wordSearchDtos) {
            averageNumberOfCharsPerWord = NumberUtils.calculateAverage(averageNumberOfCharsPerWord, totalNumberOfSearches, dto::getAverageNumberOfCharsPerWord, dto::getNumberOfSearches);
            averageNumberOfWordsPerSearch = NumberUtils.calculateAverage(averageNumberOfWordsPerSearch, totalNumberOfSearches, dto::getAverageNumberOfWordsPerSearch, dto::getNumberOfSearches);
            percentOfAllMultiWordSearches = NumberUtils.calculateAverage(percentOfAllMultiWordSearches, totalNumberOfSearches, dto::getPercentOfAllMultiWordSearches, dto::getNumberOfSearches);
            percentOfLetters = NumberUtils.calculateAverage(percentOfLetters, totalNumberOfSearches, dto::getPercentOfLetters, dto::getNumberOfSearches);
            percentOfDigits = NumberUtils.calculateAverage(percentOfDigits, totalNumberOfSearches, dto::getPercentOfDigits, dto::getNumberOfSearches);

            if (nonNull(dto.getPotentialSqlInjections()) && dto.getPotentialSqlInjections().size() > 0) {
                sqlInjections.addAll(dto.getPotentialSqlInjections());
            }
        }

        BigDecimal percentOfAllMultiWordSearches1 = BigDecimal.ZERO.equals(BigDecimal.valueOf(totalNumberOfSearches)) ? BigDecimal.ZERO : NumberUtils.divide(totalNumberOfSearches, BigDecimal.valueOf(totalNumberOfMultiWordSearches));

        return WordSearchDto.builder()
                .name(wordSearchDtos.get(FIRST_ELEMENT_INDEX).getName())
                .numberOfSearches(totalNumberOfSearches)
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch)
                .percentOfAllMultiWordSearches(percentOfAllMultiWordSearches1)
                .percentOfLetters(percentOfLetters)
                .percentOfDigits(percentOfDigits)
                .potentialSqlInjections(new ArrayList<>(sqlInjections))
                .build();
    }
}
