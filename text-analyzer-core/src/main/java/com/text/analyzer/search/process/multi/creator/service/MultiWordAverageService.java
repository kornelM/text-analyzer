package com.text.analyzer.search.process.multi.creator.service;

import java.math.BigDecimal;
import java.util.List;

public interface MultiWordAverageService {

    BigDecimal averageNumberOfWordsPerSearch(List<String> wordSearch);

    BigDecimal averageNumberOfCharsPerWord(List<String> wordSearch);
}
