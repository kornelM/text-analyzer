package com.text.analyzer.search.result.mapper;

import com.text.analyzer.response.pojo.LetterSearch;
import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class LetterSearchMapper {


    public static List<LetterSearch> mapToLetterSearches(List<LetterSearchDto> letterSearchDtos) {
        return letterSearchDtos.stream()
                .map(LetterSearchMapper::mapToLetterSearch)
                .collect(Collectors.toList());
    }

    private static LetterSearch mapToLetterSearch(LetterSearchDto d) {
        return LetterSearch.builder()
                .name(d.getName())
                .numberOfSearches(d.getNumberOfSearches())
                .percentOfDigits(d.getPercentOfDigits())
                .percentOfAllOneWordSearches(d.getPercentOfAllOneWordSearches())
                .percentOfLetters(d.getPercentOfLetters())
                .build();
    }
}
