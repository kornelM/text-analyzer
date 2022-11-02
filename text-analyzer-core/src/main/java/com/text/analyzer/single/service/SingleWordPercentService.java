package com.text.analyzer.single.service;

import java.math.BigDecimal;
import java.util.List;

public interface SingleWordPercentService {

    public BigDecimal getPercentOfLetters(List<String> singleWordSearch);

    public BigDecimal getPercentOfDigits(List<String> singleWordSearch);

    public BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, List<String> singleWordSearch);
}
