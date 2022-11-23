package com.text.analyzer.search.process.single.service;

import java.math.BigDecimal;
import java.util.List;

public interface SingleWordPercentService {

    BigDecimal percentOfLetters(List<String> singleWordSearch);

    BigDecimal percentOfDigits(List<String> singleWordSearch);

    BigDecimal percentOfThisQueryInCompareToAll(BigDecimal numberOfAllSingleWordSearches, List<String> singleWordSearch);
}
