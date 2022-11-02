package com.text.analyzer.multi.service.impl;

import com.text.analyzer.multi.service.MultiWordPercentService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

class MultiWordPercentServiceImpl implements MultiWordPercentService {

    @Override
    public BigDecimal getPercentOfLetters(Set<String> singleWordSearch) {
        return BigDecimal.valueOf(calculatePercentOfLetters(singleWordSearch));
    }

    @Override
    public BigDecimal getPercentOfDigits(Set<String> singleWordSearch) {
        return BigDecimal.valueOf(calculatePercentOfDigits(singleWordSearch));
    }

    @Override
    public BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, Set<String> singleWordSearch) {
        BigDecimal singleWordSize = new BigDecimal(singleWordSearch.size());
        return singleWordSize.divide(numberOfAllSingleWordSearches, 4, RoundingMode.UP);
    }

    private double calculatePercentOfDigits(Set<String> strings) {
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

    private double calculatePercentOfLetters(Set<String> strings) {
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
