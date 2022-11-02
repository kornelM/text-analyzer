package com.text.analyzer.multi.service;

import com.text.analyzer.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.List;

public interface MultiWordAverageService {

    BigDecimal getAverageNumberOfWordsPerSearch(List<String> wordSearch);

    BigDecimal getAverageNumberOfCharsPerWord(List<String> wordSearch);

    BigDecimal getAverageNumberOfWordsPerSearch(List<WordSearchDto> wordSearchDtos, List<String> searches);
}
