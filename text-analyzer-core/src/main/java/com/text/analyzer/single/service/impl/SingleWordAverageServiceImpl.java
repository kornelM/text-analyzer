package com.text.analyzer.single.service.impl;

import com.text.analyzer.pojo.LetterNumberEnum;
import com.text.analyzer.single.dto.LetterSearchDto;
import com.text.analyzer.single.service.SingleWordAverageService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class SingleWordAverageServiceImpl implements SingleWordAverageService {

    @Override
    public BigDecimal getAverageNumberOfChars(List<LetterSearchDto> letterSearchDtos, int totalNumberOfAllSearches) {
        BigDecimal totalNumberOfCharsInSearches = BigDecimal.valueOf(letterSearchDtos.stream()
                .map(dto -> dto.getNumberOfSearches() * LetterNumberEnum.valueOf(dto.getName()).getWordLength())
                .mapToInt(Integer::intValue)
                .sum()
        );
        BigDecimal numberOfAllSearches = BigDecimal.valueOf(totalNumberOfAllSearches);
        return totalNumberOfCharsInSearches.divide(numberOfAllSearches, 2, RoundingMode.HALF_EVEN);
    }
}
