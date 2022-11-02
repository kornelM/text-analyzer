package com.text.analyzer.single.service.impl;

import com.text.analyzer.single.service.SingleWordPercentService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

class SingleWordPercentServiceImpl implements SingleWordPercentService {

    private BigDecimal percentOfLetters;
    private BigDecimal percentOfDigits;

    @Override
    public BigDecimal getPercentOfLetters(List<String> singleWordSearch) {
        if (isNull(percentOfLetters)) {
            if (nonNull(percentOfDigits)) {
                percentOfLetters = BigDecimal.ONE.subtract(percentOfDigits);
            } else {
                percentOfLetters = BigDecimal.valueOf(calculatePercentOfLetters(singleWordSearch));
            }
        }
        return percentOfLetters;
    }

    @Override
    public BigDecimal getPercentOfDigits(List<String> singleWordSearch) {
        if (isNull(percentOfDigits)) {
            if (nonNull(percentOfLetters)) {
                percentOfDigits = BigDecimal.ONE.subtract(percentOfLetters);
            } else {
                percentOfDigits = BigDecimal.valueOf(calculatePercentOfDigits(singleWordSearch));
            }
        }
        return percentOfDigits;
    }

    @Override
    public BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, List<String> singleWordSearch) {
        BigDecimal singleWordSize = new BigDecimal(singleWordSearch.size());
        return singleWordSize.divide(numberOfAllSingleWordSearches, 4, RoundingMode.UP);
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
