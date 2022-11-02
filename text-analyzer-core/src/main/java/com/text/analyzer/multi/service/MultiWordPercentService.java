package com.text.analyzer.multi.service;

import java.math.BigDecimal;
import java.util.List;

public interface MultiWordPercentService {

    BigDecimal getPercentOfLetters(List<String> singleWordSearch);

    BigDecimal getPercentOfDigits(List<String> singleWordSearch);

    BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, List<String> singleWordSearch);
}
