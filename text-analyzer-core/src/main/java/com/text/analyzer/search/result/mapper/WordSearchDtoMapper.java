package com.text.analyzer.search.result.mapper;

import com.text.analyzer.common.dto.SearchResultDto;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@UtilityClass
public class WordSearchDtoMapper {

    public static List<WordSearchDto> mapToWordSearchDtos(List<MultiWordSearchDto> multiWordSearchDtos) {
        List<WordSearchDto> allWordSearches = multiWordSearchDtos.stream()
                .flatMap(s -> s.getWordSearches().stream())
                .collect(Collectors.toList());
        Map<String, List<WordSearchDto>> groupedWordSearches = allWordSearches.stream().collect(Collectors.groupingBy(SearchResultDto::getName));
        return groupedWordSearches.values().stream()
                .map(WordSearchDtoMapper::mergeWords)
                .collect(Collectors.toList());
    }

    private static WordSearchDto mergeWords(List<WordSearchDto> wordSearchDtos) {
        int numberOfSearches = 0;
        BigDecimal averageNumberOfCharsPerWord = BigDecimal.ZERO;
        BigDecimal averageNumberOfWordsPerSearch = BigDecimal.ZERO;
        BigDecimal percentOfAllMultiWordsSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        Set<String> sqlInjections = new HashSet<>();

        for (WordSearchDto wordSearchDto : wordSearchDtos) {
            numberOfSearches += wordSearchDto.getNumberOfSearches();
            averageNumberOfCharsPerWord = averageNumberOfCharsPerWord.add(wordSearchDto.getAverageNumberOfCharsPerWord());
            averageNumberOfWordsPerSearch = averageNumberOfWordsPerSearch.add(wordSearchDto.getAverageNumberOfWordsPerSearch());
            percentOfAllMultiWordsSearches = percentOfAllMultiWordsSearches.add(wordSearchDto.getPercentOfAllMultiWordsSearches());
            percentOfLetters = percentOfLetters.add(wordSearchDto.getPercentOfLetters());
            percentOfDigits = percentOfDigits.add(wordSearchDto.getPercentOfDigits());

            if (nonNull(wordSearchDto.getPotentialSqlInjections()) && wordSearchDto.getPotentialSqlInjections().size() > 0) {
                sqlInjections.addAll(wordSearchDto.getPotentialSqlInjections());
            }
        }

        BigDecimal numberOfDtos = BigDecimal.valueOf(wordSearchDtos.size());

        return WordSearchDto.builder()
                .name(wordSearchDtos.get(0).getName())
                .numberOfSearches(numberOfSearches)
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfAllMultiWordsSearches(percentOfAllMultiWordsSearches.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfLetters(percentOfLetters.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfDigits(percentOfDigits.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .potentialSqlInjections(new ArrayList<>(sqlInjections))
                .build();
    }
}
