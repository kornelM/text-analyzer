package com.text.analyzer.search.result.service;

import java.math.BigDecimal;
import java.util.List;

public interface ResultCalculator<T> {

    BigDecimal averageNumberOfCharsPerWord(List<T> wordSearchDtos, int totalNumberOfSearches);

    BigDecimal averageNumberOfWords(List<T> wordSearchDtos, int totalNumberOfSearches);
}
