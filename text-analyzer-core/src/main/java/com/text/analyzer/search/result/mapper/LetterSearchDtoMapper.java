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

        int totalNumberOfOneWordSearches = allLetterSearches.stream()
                .map(LetterSearchDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();

        allLetterSearches = groupedLetterSearches.values().stream()
                .map(e -> mergeLetters(e, totalNumberOfOneWordSearches))
                .collect(Collectors.toList());
        return allLetterSearches;
    }

    private static LetterSearchDto mergeLetters(List<LetterSearchDto> list, int totalNumberOfOneWordSearches) {
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        BigDecimal numberOfDtos = BigDecimal.valueOf(list.size());
        int totalNumberOfSearches = list.stream().map(LetterSearchDto::getNumberOfSearches).mapToInt(Integer::intValue).sum();

        for (LetterSearchDto dto : list) {
            percentOfLetters = percentOfLetters.add(
                    NumberUtils.divide(
                            dto.getPercentOfLetters().multiply(BigDecimal.valueOf(dto.getNumberOfSearches())),
                            totalNumberOfSearches
                    )
            );
            percentOfDigits = percentOfDigits.add(
                    NumberUtils.divide(
                            dto.getPercentOfDigits().multiply(BigDecimal.valueOf(dto.getNumberOfSearches())),
                            totalNumberOfSearches
                    )
            );
        }
        BigDecimal percentOfAllOneWordSearches = BigDecimal.ZERO.equals(numberOfDtos) ? BigDecimal.ZERO : NumberUtils.divide(totalNumberOfSearches, BigDecimal.valueOf(totalNumberOfOneWordSearches));

        return LetterSearchDto.builder()
                .name(list.get(0).getName())
                .numberOfSearches(totalNumberOfSearches)
                .percentOfAllOneWordSearches(percentOfAllOneWordSearches)
                .percentOfLetters(percentOfLetters)
                .percentOfDigits(percentOfDigits)
                .build();
    }
}
