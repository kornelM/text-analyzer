package com.text.analyzer.search.process.single.service.impl;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import com.text.analyzer.search.process.single.service.SingleWordAverageService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class SingleWordAverageServiceImpl implements SingleWordAverageService {

    @Override
    public BigDecimal getAverageNumberOfChars(List<LetterSearchDto> letterSearchDtos, int totalNumberOfAllSearches) {
        BigDecimal totalNumberOfCharsInSearches = BigDecimal.valueOf(letterSearchDtos.stream()
                .map(dto -> dto.getNumberOfSearches() * dto.getName().getWordLength())
                .mapToInt(Integer::intValue)
                .sum()
        );
        BigDecimal numberOfAllSearches = BigDecimal.valueOf(totalNumberOfAllSearches);
        return NumberUtils.divide(totalNumberOfCharsInSearches, numberOfAllSearches, 2, RoundingMode.UP);
    }
}
