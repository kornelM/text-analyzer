package com.text.analyzer.single.service;

import com.text.analyzer.single.dto.LetterSearchDto;

import java.math.BigDecimal;
import java.util.List;

public interface SingleWordAverageService {

    BigDecimal getAverageNumberOfChars(List<LetterSearchDto> letterSearchDtos, int totalNumberOfAllSearches);
}
