package com.text.analyzer.search.result.service;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class MultiWordResultCalculator implements ResultCalculator<MultiWordSearchDto> {

    @Override
    public BigDecimal averageNumberOfWords(List<MultiWordSearchDto> multiWordSearchDtos, int totalNumberOfSearches) {
        return BigDecimal.valueOf(
                multiWordSearchDtos.stream()
                        .map(MultiWordSearchDto::getWordSearches)
                        .flatMap(Collection::stream)
                        .map(f -> NumberUtils.divide(
                                        f.getAverageNumberOfWordsPerSearch().multiply(BigDecimal.valueOf(f.getNumberOfSearches())),
                                        totalNumberOfSearches
                                )
                        )
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum()
        );
    }

    @Override
    public BigDecimal averageNumberOfCharsPerWord(List<MultiWordSearchDto> multiWordSearchDtos, int totalNumberOfSearches) {
        return BigDecimal.valueOf(multiWordSearchDtos.stream()
                .map(MultiWordSearchDto::getWordSearches)
                .flatMap(Collection::stream)
                .map(s -> NumberUtils.divide(
                                s.getAverageNumberOfCharsPerWord().multiply(BigDecimal.valueOf(s.getNumberOfSearches())),
                                totalNumberOfSearches
                        )
                )
                .mapToDouble(BigDecimal::doubleValue)
                .sum());
    }

}
