package com.text.analyzer.search.result.mapper;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.response.pojo.LetterNumberEnum;
import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class LetterSearchDtoMapper {

    public static List<LetterSearchDto> mapToLetterSearchDtos(List<SingleWordSearchDto> singleWordSearchDtos) {
        List<LetterSearchDto> allLetterSearches = singleWordSearchDtos.stream()
                .flatMap(s -> s.getLetterSearches().stream())
                .collect(Collectors.toList());
        Map<LetterNumberEnum, List<LetterSearchDto>> groupedLetterSearches = allLetterSearches.stream().collect(Collectors.groupingBy(LetterSearchDto::getName));
        allLetterSearches = groupedLetterSearches.values().stream()
                .map(LetterSearchDtoMapper::mergeLetters)
                .collect(Collectors.toList());
        return allLetterSearches;
    }

    private static LetterSearchDto mergeLetters(List<LetterSearchDto> list) {
        int numberOfSearches = 0;
        BigDecimal percentOfAllOneWordSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        BigDecimal numberOfDtos = BigDecimal.valueOf(list.size());

        for (LetterSearchDto dto : list) {
            numberOfSearches += dto.getNumberOfSearches();
            percentOfAllOneWordSearches = percentOfAllOneWordSearches.add(dto.getPercentOfAllOneWordSearches());
            percentOfLetters = percentOfLetters.add(dto.getPercentOfLetters());
            percentOfDigits = percentOfDigits.add(dto.getPercentOfDigits());
        }

        return LetterSearchDto.builder()
                .name(list.get(0).getName())
                .numberOfSearches(numberOfSearches)
                .percentOfAllOneWordSearches(NumberUtils.divide(percentOfAllOneWordSearches, numberOfDtos))
                .percentOfLetters(NumberUtils.divide(percentOfLetters, numberOfDtos))
                .percentOfDigits(NumberUtils.divide(percentOfDigits, numberOfDtos))
                .build();
    }
}
