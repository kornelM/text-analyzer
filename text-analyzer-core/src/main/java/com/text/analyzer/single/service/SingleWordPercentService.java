package com.text.analyzer.single.service;

import java.math.BigDecimal;
import java.util.Set;

public interface SingleWordPercentService {

    public BigDecimal getPercentOfLetters(Set<String> singleWordSearch);

    public BigDecimal getPercentOfDigits(Set<String> singleWordSearch);

    public BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, Set<String> singleWordSearch);
}
