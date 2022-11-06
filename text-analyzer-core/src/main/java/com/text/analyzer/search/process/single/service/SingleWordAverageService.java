package com.text.analyzer.search.process.single.service;

import com.text.analyzer.search.process.single.dto.LetterSearchDto;

import java.math.BigDecimal;
import java.util.List;

public interface SingleWordAverageService {

    BigDecimal getAverageNumberOfChars(List<LetterSearchDto> letterSearchDtos, int totalNumberOfAllSearches);
}
