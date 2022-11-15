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

    public static List<WordSearchDto> mapToWordSearchDtos(List<MultiWordSearchDto> multiWordSearchDtos) {
        List<WordSearchDto> allWordSearches = multiWordSearchDtos.stream()
                .flatMap(s -> s.getWordSearches().stream())
                .collect(Collectors.toList());
        Map<WordSearchEnum, List<WordSearchDto>> groupedWordSearches = allWordSearches.stream().collect(Collectors.groupingBy(WordSearchDto::getName));

        int sum = groupedWordSearches.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(s -> s.getNumberOfSearches())
                .sum();

        return groupedWordSearches.values().stream()
                .map(a -> WordSearchDtoMapper.mergeWords(a, sum))
                .collect(Collectors.toList());
    }

    private static WordSearchDto mergeWords(List<WordSearchDto> wordSearchDtos, int totalNumberOfMultiWordSearches) {
        BigDecimal averageNumberOfCharsPerWord = BigDecimal.ZERO;
        BigDecimal averageNumberOfWordsPerSearch = BigDecimal.ZERO;
        BigDecimal percentOfAllMultiWordsSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        Set<String> sqlInjections = new HashSet<>();
        int totalNumberOfSearches = wordSearchDtos.stream().map(WordSearchDto::getNumberOfSearches).map(BigDecimal::valueOf).mapToInt(BigDecimal::intValue).sum();

        for (WordSearchDto wordSearchDto : wordSearchDtos) {
            averageNumberOfCharsPerWord = averageNumberOfCharsPerWord.add(
                    NumberUtils.divide(
                            wordSearchDto.getAverageNumberOfCharsPerWord().multiply(BigDecimal.valueOf(wordSearchDto.getNumberOfSearches())),
                            totalNumberOfSearches
                    )
            );
            averageNumberOfWordsPerSearch = averageNumberOfWordsPerSearch.add(
                    NumberUtils.divide(
                            wordSearchDto.getAverageNumberOfWordsPerSearch().multiply(BigDecimal.valueOf(wordSearchDto.getNumberOfSearches())),
                            totalNumberOfSearches
                    )
            );
            percentOfAllMultiWordsSearches = percentOfAllMultiWordsSearches.add(
                    NumberUtils.divide(
                            wordSearchDto.getPercentOfAllMultiWordsSearches().multiply(BigDecimal.valueOf(wordSearchDto.getNumberOfSearches())),
                            totalNumberOfSearches
                    )
            );
            percentOfLetters = percentOfLetters.add(
                    NumberUtils.divide(
                            wordSearchDto.getPercentOfLetters().multiply(BigDecimal.valueOf(wordSearchDto.getNumberOfSearches())),
                            totalNumberOfSearches
                    )
            );
            percentOfDigits = percentOfDigits.add(
                    NumberUtils.divide(
                            wordSearchDto.getPercentOfDigits().multiply(BigDecimal.valueOf(wordSearchDto.getNumberOfSearches())),
                            totalNumberOfSearches
                    )
            );

            if (nonNull(wordSearchDto.getPotentialSqlInjections()) && wordSearchDto.getPotentialSqlInjections().size() > 0) {
                sqlInjections.addAll(wordSearchDto.getPotentialSqlInjections());
            }
        }

        BigDecimal percentOfAllMultiWordsSearches1 = BigDecimal.ZERO.equals(BigDecimal.valueOf(totalNumberOfSearches)) ? BigDecimal.ZERO : NumberUtils.divide(totalNumberOfSearches, BigDecimal.valueOf(totalNumberOfMultiWordSearches));

        return WordSearchDto.builder()
                .name(wordSearchDtos.get(0).getName())
                .numberOfSearches(totalNumberOfSearches)
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch)
                .percentOfAllMultiWordsSearches(percentOfAllMultiWordsSearches1)
                .percentOfLetters(percentOfLetters)
                .percentOfDigits(percentOfDigits)
                .potentialSqlInjections(new ArrayList<>(sqlInjections))
                .build();
    }
}
