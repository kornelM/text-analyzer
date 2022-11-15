package com.text.analyzer.search.result.service;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;

import java.math.BigDecimal;
import java.util.List;

public class SingleWordResultCalculator implements ResultCalculator<SingleWordSearchDto> {

    @Override
    public BigDecimal averageNumberOfCharsPerWord(List<SingleWordSearchDto> singleWordSearchDtos, int totalNumberOfSearches) {
        return BigDecimal.valueOf(
                singleWordSearchDtos.stream()
                        .map(s -> NumberUtils.divide(
                                        s.getAverageNumberOfCharsPerWord().multiply(BigDecimal.valueOf(s.getNumberOfSearches())),
                                        totalNumberOfSearches
                                )
                        )
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum()
        );
    }

    @Override
    public BigDecimal averageNumberOfWords(List<SingleWordSearchDto> singleWordSearchDtos, int totalNumberOfSearches) {
        return BigDecimal.ONE;
    }
}
