package com.text.analyzer.search.result.mapper;

import com.text.analyzer.response.pojo.WordSearch;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class WordSearchMapper {

    public static List<WordSearch> mapToWordSearches(List<WordSearchDto> allWordSearches) {
        return allWordSearches.stream()
                .map(WordSearchMapper::mapToWordSearch)
                .collect(Collectors.toList());
    }

    public static WordSearch mapToWordSearch(WordSearchDto d) {
        return WordSearch.builder()
                .name(d.getName())
                .numberOfSearches(d.getNumberOfSearches())
                .percentOfAllMultiWordSearches(d.getPercentOfAllMultiWordsSearches())
                .percentOfLettersPerSearch(d.getPercentOfLetters())
                .percentOfDigitsPerSearch(d.getPercentOfDigits())
                .averageNumberOfCharsPerWord(d.getAverageNumberOfCharsPerWord())
                .averageNumberOfWords(d.getAverageNumberOfWordsPerSearch())
                .build();
    }
}
