package com.text.analyzer.search.process.multi.service;

import com.text.analyzer.search.process.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.List;

public interface MultiWordAverageService {

    BigDecimal getAverageNumberOfWordsPerSearch(List<String> wordSearch);

    BigDecimal getAverageNumberOfCharsPerWord(List<String> wordSearch);

    BigDecimal getAverageNumberOfWordsPerSearch(List<WordSearchDto> wordSearchDtos, List<String> searches);
}
