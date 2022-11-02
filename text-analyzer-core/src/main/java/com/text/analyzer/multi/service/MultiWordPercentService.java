package com.text.analyzer.multi.service;

import java.math.BigDecimal;
import java.util.Set;

public interface MultiWordPercentService {

    BigDecimal getPercentOfLetters(Set<String> singleWordSearch);

    BigDecimal getPercentOfDigits(Set<String> singleWordSearch);

    BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, Set<String> singleWordSearch);
}
