package com.text.analyzer.search.process.single.service.impl;

import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.search.process.single.service.SingleWordPercentService;

import java.math.BigDecimal;
import java.util.List;

public class SingleWordPercentServiceImpl implements SingleWordPercentService {

    @Override
    public BigDecimal percentOfLetters(List<String> singleWordSearch) {
        return BigDecimal.valueOf(calculatePercentOfLetters(singleWordSearch));
    }

    @Override
    public BigDecimal percentOfDigits(List<String> singleWordSearch) {
        return BigDecimal.valueOf(calculatePercentOfDigits(singleWordSearch));
    }

    @Override
    public BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, List<String> singleWordSearch) {
        BigDecimal singleWordSize = new BigDecimal(singleWordSearch.size());
        return NumberUtils.divide(singleWordSize, numberOfAllSingleWordSearches);
    }

    private double calculatePercentOfDigits(List<String> strings) {
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

    private double calculatePercentOfLetters(List<String> strings) {
        return strings.stream()
                .map(s -> {
                    int charsNumber = 0;
                    for (char c : s.toCharArray()) {
                        if (!Character.isDigit(c)) {
                            charsNumber++;
                        }
                    }
                    return (double) charsNumber / s.length();
                })
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(1);
    }
}
