package com.text.analyzer.search.process.multi.creator.service;

import java.math.BigDecimal;
import java.util.List;

public interface MultiWordPercentService {

    BigDecimal percentOfLetters(List<String> singleWordSearch);

    BigDecimal percentOfDigits(List<String> singleWordSearch);

    BigDecimal percentOfThisQueryInCompareToAll(int numberOfAllSingleWordSearches, List<String> singleWordSearch);
}
