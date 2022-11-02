package com.text.analyzer.multi.dto;

import com.text.analyzer.common.dto.SearchResultDto;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class WordSearchDto extends SearchResultDto {
    private BigDecimal averageNumberOfCharsPerWord;
    private BigDecimal averageNumberOfWordsPerSearch;
    private BigDecimal percentOfAllMultiWordsSearches;
    private BigDecimal percentOfLetters;
    private BigDecimal percentOfDigits;
}
