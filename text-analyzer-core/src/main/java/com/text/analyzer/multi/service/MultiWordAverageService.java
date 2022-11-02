package com.text.analyzer.multi.service;

import com.text.analyzer.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface MultiWordAverageService {

    BigDecimal getAverageNumberOfWordsPerSearch(Set<String> wordSearch);

    BigDecimal getAverageNumberOfCharsPerWord(Set<String> wordSearch);

    BigDecimal getAverageNumberOfWordsPerSearch(List<WordSearchDto> wordSearchDtos, List<String> searches);
}
