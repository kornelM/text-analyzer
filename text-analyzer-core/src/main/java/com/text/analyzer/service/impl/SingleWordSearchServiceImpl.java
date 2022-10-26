package com.text.analyzer.service.impl;

import com.text.analyzer.dto.LetterSearchDto;
import com.text.analyzer.dto.SingleWordSearchDto;
import com.text.analyzer.pojo.LetterNumberEnum;
import com.text.analyzer.service.SingleWordSearchService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SingleWordSearchServiceImpl implements SingleWordSearchService {

    @Override
    public SingleWordSearchDto processSearches(List<String> searches) {
        List<LetterSearchDto> letterSearches = new ArrayList<>();

        //group searches
        Map<Integer, Set<String>> separatedStringsByLength = getStringsToIntNonConcurrent(searches);
        BigDecimal numberOfAllSingleWordSearches = new BigDecimal(separatedStringsByLength.values().stream()
                .map(Set::size)
                .mapToInt(Integer::intValue)
                .sum()
        );
        //perform computing
        for (Integer stringLength : separatedStringsByLength.keySet()) {
            Set<String> strings = separatedStringsByLength.get(stringLength);
            BigDecimal percentOfAllOneWordSearches = new BigDecimal(strings.size()).divide(numberOfAllSingleWordSearches, 4, RoundingMode.UP);

            double percentOfNumbersInLetters = getPercentOfNumbersInLetters(strings);
            BigDecimal percentOfDigits = BigDecimal.valueOf(percentOfNumbersInLetters).setScale(2, RoundingMode.UP);

            letterSearches.add(LetterSearchDto.builder()
                    .name(LetterNumberEnum.valueOf(stringLength))
                    .percentOfAllOneWordSearches(percentOfAllOneWordSearches)
                    .percentOfDigits(percentOfDigits)
//                    .percentOfLetters()
//                    .numberOfSearches()
                    .build());
        }


        return SingleWordSearchDto.builder()
                .letterSearches(letterSearches)
                .build();
    }

    public double getPercentOfNumbersInLetters(Set<String> strings) {
        return strings.stream()
                .map(s -> {
                    int charsNumber = 0;
                    for (char c : s.toCharArray()) {
                        if (Character.isDigit(c)) {
                            charsNumber++;
                        }
                    }
                    return (double) charsNumber / s.length();
                })
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(1);
    }

    public Map<Integer, Set<String>> getStringsToIntNonConcurrent(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingBy(
                                String::length,
                                TreeMap::new,
                                Collectors.toSet()
                        )
                );
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
